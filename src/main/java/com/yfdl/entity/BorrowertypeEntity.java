package com.yfdl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * (Borrowertype)表实体类
 *
 * @author makejava
 * @since 2022-11-03 16:16:12
 */
@Data
@NoArgsConstructor
@TableName("BorrowerType")
public class BorrowertypeEntity implements Serializable {
    private static final long serialVersionUID = 918078036355456782L;

    

        private String type;

        private Integer number;

}
