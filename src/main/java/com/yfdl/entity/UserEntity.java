package com.yfdl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2022-11-03 16:16:28
 */
@Data
@NoArgsConstructor
@TableName("User")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 386832278142401369L;

    
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

}
