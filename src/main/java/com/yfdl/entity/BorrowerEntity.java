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
 * (Borrower)表实体类
 *
 * @author makejava
 * @since 2022-11-03 17:44:05
 */
@Data
@NoArgsConstructor
@TableName("Borrower")
public class BorrowerEntity implements Serializable {
    private static final long serialVersionUID = 880914387238547696L;

    
        /**
    * 借阅号
    */
        private String cardnum;

        /**
    * 姓名
    */
            private String cardname;

        /**
    * 性别
    */
            private Integer sex;

        /**
    * 身份证号
    */
            private String personnum;

        /**
    * 单位
    */
            private String workplace;

        /**
    * 家庭地址
    */
            private String address;

        /**
    * 联系电话
    */
            private String phone;

        /**
    * 借阅者类型
    */
            private String type;

        /**
    * 办证日期
    */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private Date signdate;

        /**
    * 已借书数目
    */
            private Integer borrowernum;

        /**
    * 是否挂失
    */
            private Integer loss;

}
