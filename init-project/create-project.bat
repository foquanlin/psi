@echo off
if "%1" == "" (
 echo ��������Ŀ����,����: cms
 pause
 goto END
)

:START
ECHO ��ʼ������Ŀ %~0


xcopy /d/E admin ..\tongyi-%~1-admin\
xcopy /d/E api ..\tongyi-%~1-api\
xcopy /d/E core ..\tongyi-%~1-core\
xcopy /d/E service ..\tongyi-%~1-service\
ECHO ������� %~0
 pause

:END