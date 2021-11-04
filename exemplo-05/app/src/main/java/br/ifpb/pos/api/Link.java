package br.ifpb.pos.api;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 10:51:21
 */
public class Link {

    private String href;
    private String rel;

    public Link() {
        this("","");
    }

    public Link(String href,String rel) {
        this.href = href;
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

}
