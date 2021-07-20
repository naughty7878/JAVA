package com.test.springdata.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "shopping", shards = 2, replicas = 1)
public class Product {
    //商品唯一标识
    //必须有 id,这里的 id 是全局唯一的标识，等同于 es 中的"_id"
    @Id
    private Long id;
    //商品名称
    /**
     * type : 字段数据类型
     * analyzer : 分词器类型
     * index : 是否索引(默认:true) * Keyword : 短语,不进行分词 */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;
    //分类名称
    @Field(type = FieldType.Keyword)
    private String category;
    // 商品价格
    @Field(type = FieldType.Double)
    private Double price;
    //图片地址
    @Field(type = FieldType.Keyword, index = false)
    private String images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", images='" + images + '\'' +
                '}';
    }
}
