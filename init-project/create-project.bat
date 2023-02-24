@echo off
if "%1" == "" (
 echo 请输入项目名称,例如: cms
 pause
 goto END
)

:START
ECHO 开始创建项目 %~0


xcopy /d/E admin ..\tongyi-%~1-admin\
xcopy /d/E api ..\tongyi-%~1-api\
xcopy /d/E core ..\tongyi-%~1-core\
xcopy /d/E service ..\tongyi-%~1-service\
ECHO 创建完成 %~0
 pause

:END