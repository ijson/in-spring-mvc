/**
 * @author 崔永旭
 * 共16个
 **/

	$.extend($.fn.validatebox.defaults.rules, {
		//验证身份证
		//15位身份证号,或者18位身份证号,其中最后一位可以为字符
		idCard : {
			validator : function(value) {
			return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
			},
			message : '身份证号码格式不正确'
		},
		//设置参数为最小长度
		minLength: {
			validator: function(value, param){
			return value.length >= param[0];
			},
			message: '请输入至少{0}个字符.'
		},
		//验证电话号码(区号-电话号码-分机号)或(电话号码-分机号)或((区号)-电话号码)
		phone : {  
			validator : function(value) {
			return /^((\d{4}|\d{3})(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|\(\d{3}\)-(\d{7,8}))$/i.test(value);
			},
			message : '格式不正确,请使用下面格式:(区号-电话号码-分机号)或(电话号码-分机号)或((区号)-电话号码)'
		},
		//验证手机号码,以13,15,18开头
		mobile : {
			validator : function(value) {
			return /^(13|15|18)\d{9}$/i.test(value);
			},
			message : '手机号码格式不正确'
		},
		//验证传真
		faxno : {
			validator : function(value) {
			return /^((\(\d{3,4}\))|(\d{3}\-))?(\(0\d{3,4}\)|0\d{3,4}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
			},
			message : '传真号码不正确'
		},
		//验证整数或小数
		intOrFloat : {
			validator : function(value) {
			return /^\d+(\.\d+)?$/i.test(value);
			},
			message : '请输入数字，并确保格式正确'
		},
		//验证货币
		currency : {
			validator : function(value) {
			return /^\d+(\.\d+)?$/i.test(value);
			},
			message : '货币格式不正确'
		},
		//验证QQ,从10000开始
		qq : {
			validator : function(value) {
			return /^[1-9]\d{4,9}$/i.test(value);
			},
			message : 'QQ号码格式不正确'
		},
		//验证整数
		integer : {
			validator : function(value) {
			return /^[+]?[1-9]+\d*$/i.test(value);
			},
			message : '请输入整数'
		},
		//验证年龄,年龄必须是0到120之间的整数
		age : {
			  
			validator : function(value) {
			return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
			},
			message : '年龄必须是0到120之间的整数'
		},
		//验证中文
		chinese : {
			validator : function(value) {
			return /^[\Α-\￥]+$/i.test(value);
			},
			message : '请输入中文'
		},
		//验证英语
		english : {
			validator : function(value) {
			return /^[A-Za-z]+$/i.test(value);
			},
			message : '请输入英文'
		},
		//验证用户名
		username : {
			validator : function(value) {
			return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
			},
			message : '用户名不合法(字母开头,允许6-16字节,允许字母数字下划线)'
		},
		//验证邮政编码
		zip : {
			validator : function(value) {
			return /^[1-9]\d{5}$/i.test(value);
			},
			message : '邮政编码格式不正确'
		},
		//验证IP地址
		ip : {
			validator : function(value) {
			return /^((25[0-5]|2[0-4]\d|1?\d?\d)\.){3}(25[0-5]|2[0-4]\d|1?\d?\d)/i.test(value);
			},
			message : 'IP地址格式不正确'
		},
		//验证日期
		date : {
			validator : function(value) {
			return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
			},
			message : '清输入合适的日期格式,格式yyyy-MM-dd或yyyy-M-d'
		}
	});
