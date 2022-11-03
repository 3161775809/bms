package com.yfdl.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * (Book)表实体类
 *
 * @author makejava
 * @since 2022-11-03 16:14:26
 */
@Data
@NoArgsConstructor
@TableName("Book")
public class BookEntity implements Serializable {
    private static final long serialVersionUID = 512144331312387595L;

    
        /**
    * 图书号
    */
        private String booknum;

        /**
    * 书名
    */
            private String bookname;

        /**
    * 作者
    */
            private String author;

        /**
    * 出版社
    */
            private String press;

        /**
    * 出版日期
    */
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
            private Date pressdate;

        /**
    * 单价
    */
            private Integer price;

        /**
    * 图书类别
    */
            private String type;

        /**
    * 存放位置
    */
            private String site;

        /**
    * 入库日期
    */
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
            private Date regdate;

        /**
    * 是否借出
    */
            private Integer islend;

}
