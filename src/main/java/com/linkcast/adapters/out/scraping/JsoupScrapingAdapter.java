package com.linkcast.adapters.out.scraping;

import com.linkcast.application.port.out.ScrapingPort;
import com.linkcast.domain.exception.RegraDeNegocioException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Adaptador para extração de conteúdo textual de páginas web usando Jsoup.
 */
@Component
public class JsoupScrapingAdapter implements ScrapingPort {

    private static final int TIMEOUT_MS = 15000;
    private static final int MAX_CONTENT_LENGTH = 100000; // 100KB de texto
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";

    @Override
    public String extrairTexto(String url) {
        validarUrl(url);
        
        try {
            Document doc = conectarEObterDocumento(url);
            removerElementosIndesejados(doc);
            Element elementoPrincipal = obterElementoPrincipal(doc);
            String texto = extrairTextoDoElemento(elementoPrincipal);
            return normalizarTexto(texto);
        } catch (RegraDeNegocioException e) {
            throw e;
        } catch (SocketTimeoutException e) {
            throw new RegraDeNegocioException("Timeout ao acessar a URL. O servidor demorou muito para responder.");
        } catch (UnknownHostException e) {
            throw new RegraDeNegocioException("Não foi possível resolver o endereço da URL. Verifique se o domínio existe.");
        } catch (IOException e) {
            throw new RegraDeNegocioException("Erro ao acessar a URL: " + e.getMessage());
        }
    }

    private void validarUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new RegraDeNegocioException("URL não pode ser vazia.");
        }
        
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            throw new RegraDeNegocioException("URL inválida: " + url);
        }
    }

    private Document conectarEObterDocumento(String url) throws IOException {
        return Jsoup.connect(url)
                .timeout(TIMEOUT_MS)
                .userAgent(USER_AGENT)
                .followRedirects(true)
                .maxBodySize(5 * 1024 * 1024) // 5MB máximo
                .get();
    }

    private void removerElementosIndesejados(Document doc) {
        // Remove elementos estruturais e de navegação
        doc.select("header, nav, footer, aside").remove();
        
        // Remove scripts e estilos
        doc.select("script, style, noscript").remove();
        
        // Remove iframes e objetos embutidos
        doc.select("iframe, object, embed").remove();
        
        // Remove elementos comuns de publicidade
        doc.select("[class*=ad-], [class*=ads-], [class*=advert], [class*=banner], [class*=sidebar], [class*=menu], [class*=nav], [class*=footer], [class*=header]").remove();
        doc.select("[id*=ad-], [id*=ads-], [id*=advert], [id*=banner], [id*=sidebar], [id*=menu], [id*=nav], [id*=footer], [id*=header]").remove();
        
        // Remove comentários e elementos de compartilhamento
        doc.select(".comment, .comments, .share, .social, .related, .recommended").remove();
        doc.select("[role=navigation], [role=banner], [role=contentinfo], [role=complementary]").remove();
    }

    private Element obterElementoPrincipal(Document doc) {
        // Tenta encontrar o elemento principal na seguinte ordem:
        // 1. <article>
        // 2. <main>
        // 3. Elementos comuns de conteúdo
        // 4. <body>
        
        Element article = doc.selectFirst("article");
        if (article != null) {
            return article;
        }
        
        Element main = doc.selectFirst("main");
        if (main != null) {
            return main;
        }
        
        // Tenta encontrar por classes comuns de conteúdo
        Element content = doc.selectFirst(".content, .post-content, .article-content, .entry-content, .post-body, .article-body");
        if (content != null) {
            return content;
        }
        
        // Tenta encontrar por IDs comuns de conteúdo
        content = doc.selectFirst("#content, #post-content, #article-content, #entry-content");
        if (content != null) {
            return content;
        }
        
        // Último recurso: body
        return doc.body();
    }

    private String extrairTextoDoElemento(Element elemento) {
        if (elemento == null) {
            return "";
        }
        
        // Extrai o texto mantendo quebras de linha para parágrafos
        StringBuilder texto = new StringBuilder();
        
        Elements paragrafos = elemento.select("p, h1, h2, h3, h4, h5, h6, li, td, th, blockquote, pre");
        
        if (!paragrafos.isEmpty()) {
            for (Element p : paragrafos) {
                String textoParagrafo = p.text().trim();
                if (!textoParagrafo.isEmpty()) {
                    texto.append(textoParagrafo).append("\n\n");
                }
            }
        } else {
            texto.append(elemento.text());
        }
        
        return texto.toString();
    }

    private String normalizarTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "";
        }
        
        String normalizado = texto
                // Remove múltiplos espaços consecutivos
                .replaceAll("[ \\t]+", " ")
                // Remove múltiplas quebras de linha consecutivas (mantém máximo 2)
                .replaceAll("\\n{3,}", "\n\n")
                // Remove espaços no início e fim de cada linha
                .replaceAll("(?m)^\\s+|\\s+$", "")
                // Limita o tamanho do texto
                .trim();
        
        if (normalizado.length() > MAX_CONTENT_LENGTH) {
            normalizado = normalizado.substring(0, MAX_CONTENT_LENGTH) + "...";
        }
        
        return normalizado;
    }
}