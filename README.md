# RmAppFromEclipse

为eclipse提供一键删除App功能。

一：

    通过源码生成jar文件，放置于eclipse下的plugins目录中；

二：

    生成rm.ini，放置在eclipse根目录中：

        1.ini文件必须为UTF-8格式；

        2.添加需要删除的应用包名，前缀不可改；

        3.本机adb目录。
        

    ini文件格式如下：

        packagename:com.sina.weibo

        adbpath:/home/bmb/sdk/platform-tools/adb
