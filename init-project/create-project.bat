@echo off
if "%1" == "" (
 echo 请输入项目名称,例如: cms
 pause
 goto END
)

:START
ECHO 开始创建项目 %~0


xcopy /S/d/E admin ..\tongyi-%~1-admin\
xcopy /S/d/E api\ ..\tongyi-%~1-api\
xcopy /S/d/E core\ ..\tongyi-%~1-core\
xcopy /S/d/E service\ ..\tongyi-%~1-service\


:END