<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Freemarker 测试</title>
</head>
<body>
[@cms_channel_list count='7']
    <ul class="index-title">
        <li class="title-red Medium">首页</li>
        [#list tag_list as a]
            <li><a class="Regular" href="${a.url!}">${a.name!}</a></li>
        [/#list]
    </ul>
[/@cms_channel_list]
</body>
</html>