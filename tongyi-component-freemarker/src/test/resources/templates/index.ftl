<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Freemarker 测试</title>
</head>
<body>
<#--这是 freemarker 注释，不会输出到文件中-->
<h1>${name}； ${message}</h1>
<hr>
<#--assign-->
<#--简单类型-->
<#assign linkman="李四"/>
联系人：${linkman}
<#assign info={"tel":"110","sex":"男"}/>
电话：${info.tel};性别：${info.sex}
<hr>
<#--include-->
<#include "header.ftl"/>
<hr>
<#--if-->
<#assign bool=false/>
<#if bool>
    bool为true
<#else>
    bool为false
</#if>
<hr>
<#--list-->
<#list goodsList as fruit>
    索引：${fruit_index},
    水果：${fruit.name};
    价格：${fruit.price}<br>
</#list>
<hr>
<#--获取集合总记录数-->
总共${goodsList?size}条记录
<#--集合中索引为1的name属性-->
${goodsList[1].name}
<#--集合中第一个元素的name属性-->
${goodsList?first.name}
<hr>
<#assign str="{'id':'123','text':'文本'}"/>
<#assign jsonObj=str?eval/>
id为：${jsonObj.id};text为：${jsonObj.text}<p></p>
<hr>
当前日期：${today?date}<br>
<hr>
当前时间：${today?time}<br>
<hr>
当前日期+时间：${today?datetime}
<hr>
格式化显示当前日期时间：${today?string('yyyy年MM月dd日 HH:mm:ss')}
<hr>
<#--数值显示处理-->
${number}
<hr>
${number?c}<p></p>
<hr>
${strs!"strs空值的默认显示值"}
<hr>
<#if strs??>
    str变量存在
<#else >
    strs变量不存在
</#if>

</body>
</html>