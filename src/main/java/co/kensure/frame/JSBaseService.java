package co.kensure.frame;

import java.util.Date;



/**
 * Created by fankd on 2017/6/5.
 */
public abstract class JSBaseService {
	
	/**
	 * 表名称
	 * @param obj
	 * @param tableName
	 */
	protected void beforeInsert(BaseInfo obj){
		Date date = new Date();
		if(obj.getCreatedTime() == null){
			obj.setCreatedTime(date);
		}
		if(obj.getUpdatedTime() == null){
			obj.setUpdatedTime(date);
		}		
	}
	
	protected void beforeUpdate(BaseInfo obj){
		Date date = new Date();
		obj.setUpdatedTime(date);
	}
}
