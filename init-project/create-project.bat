@echo off
if "%1" == "" (
 echo ��������Ŀ����,����: cms
 pause
 goto END
)

:START
ECHO ��ʼ������Ŀ %~0


xcopy /S/d/E admin ..\tongyi-%~1-admin\
xcopy /S/d/E api\ ..\tongyi-%~1-api\
xcopy /S/d/E core\ ..\tongyi-%~1-core\
xcopy /S/d/E service\ ..\tongyi-%~1-service\


:END