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
 * (Borrlend)表实体类
 *
 * @author makejava
 * @since 2022-11-03 16:15:44
 */
@Data
@NoArgsConstructor
@TableName("BorrLend")
public class BorrlendEntity implements Serializable {
    private static final long serialVersionUID = 173135343485445425L;


            private String cardnum;

            private String booknum;

            private String blCardname;

            private String blBookname;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
            private Date borrowday;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
            private Date returnday;

            private Integer finemoney;

}
