package org.lbq.searchboot.bean;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * null
 * @TableName t_poem
 */
@Data
@Document(indexName = "poems",type = "poem")
public class Poem implements Serializable {
    /**
     * 
     */
    @Id
    private String id;

    /**
     * 
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;

    /**
     * 
     */
    @Field(type=FieldType.Keyword)
    private String author;

    /**
     * 
     */
    @Field(type=FieldType.Text,analyzer = "ik_max_word")
    private String type;

    /**
     * 
     */
    @Field(type=FieldType.Text,analyzer = "ik_max_word")
    private String content;

    /**
     * 
     */
    @Field(type=FieldType.Keyword)
    private String href;

    /**
     * 
     */
    private String authordes;

    /**
     * 
     */
    private String origin;

    /**
     * 
     */
    private String categoryid;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Poem other = (Poem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getHref() == null ? other.getHref() == null : this.getHref().equals(other.getHref()))
            && (this.getAuthordes() == null ? other.getAuthordes() == null : this.getAuthordes().equals(other.getAuthordes()))
            && (this.getOrigin() == null ? other.getOrigin() == null : this.getOrigin().equals(other.getOrigin()))
            && (this.getCategoryid() == null ? other.getCategoryid() == null : this.getCategoryid().equals(other.getCategoryid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getHref() == null) ? 0 : getHref().hashCode());
        result = prime * result + ((getAuthordes() == null) ? 0 : getAuthordes().hashCode());
        result = prime * result + ((getOrigin() == null) ? 0 : getOrigin().hashCode());
        result = prime * result + ((getCategoryid() == null) ? 0 : getCategoryid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", author=").append(author);
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", href=").append(href);
        sb.append(", authordes=").append(authordes);
        sb.append(", origin=").append(origin);
        sb.append(", categoryid=").append(categoryid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}