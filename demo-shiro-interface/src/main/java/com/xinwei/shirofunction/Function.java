package com.xinwei.shirofunction;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
/**
 * 系统功能目录
 * @author ouburikou
 *
 */
@Entity
@Table(name = "shiro_function")
@Data
public class Function implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private Integer pid;
	private Date createTime;
	private String functionName;
	private String permissionName;//securityApply:templetUpload
}
