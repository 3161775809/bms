package com.yfdl.Dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertUserDto {

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 是否是管理员
     */
    private Integer isadmin;

    private String cardnum;

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
