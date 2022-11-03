package com.yfdl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * (Booktype)表实体类
 *
 * @author makejava
 * @since 2022-11-03 21:25:14
 */
@Data
@NoArgsConstructor
@TableName("BookType")
public class BooktypeEntity implements Serializable {
    private static final long serialVersionUID = 516865852059107023L;

    
        /**
    * 图书类别
    */

        private String type;

        /**
    * 可借天数
    */
            private Integer daynum;

        /**
    * 图书超期每天罚款钱
    */
            private Double price;

}
