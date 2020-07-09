ISSUE 1:

Cors error on browser

Solution:
Goto chrome installed path pn command prompt and enter as following, 

C:\Users\Admin>cd C:\Users\Admin\AppData\Local\Google\Chrome\Application

C:\Users\Admin\AppData\Local\Google\Chrome\Application>chrome.exe --user-data-dir="C:/Chrome dev session" --disable-web-security

ISSUE 2: [01:35:09] E/launcher - SessionNotCreatedError: session not created: This version of ChromeDriver only supports Chrome version 83


Solution : Updated chrome to 83

ISSUE 3: Chrome was updated, then chrome got crashed.

 E/launcher - unknown error: Chrome failed to start: crashed.
  (unknown error: DevToolsActivePort file doesn't exist)
  (The process started from chrome location C:\Users\Admin\AppData\Local\Google\Chrome\Application\chrome.exe is no longer running, so ChromeDriver is assuming that Chrome has crashed.)
  (Driver info: chromedriver=83.0.4103.14 (be04594a2b8411758b860104bc0a1033417178be-refs/branch-heads/4103@{#119}),platform=Windows NT 10.0.18363 x86_64)
[12:20:09] E/launcher - WebDriverError: unknown error: Chrome failed to start: crashed.
  (unknown error: DevToolsActivePort file doesn't exist)
  (The process started from chrome location C:\Users\Admin\AppData\Local\Google\Chrome\Application\chrome.exe is no longer running, so ChromeDriver is assuming that Chrome has crashed.)

Solution:

delete C:\Users\Admin\AppData\Local\Google\Chrome\Application folder

ISSUE 5: Cannot able to bind to port 3306

Solution :You have to stop the service running in tcp port locally inorder to access it on docker....


ISSUE 5.1 : inorder to run mysql stop it on docker n start  here..


ISSUE 6: In docker if we start a service , for example mysql service is up then we cant access it on local machine .. so stop that container n start on local... u wont see ur db if u connect locally...


Solution : netstat -ano | findstr :PORT_NUMBER

(Replace PORT_NUMBER with the port number you want, but keep the colon)

Locate the PID of the process that's using the port you want.

Next, run the following command:

taskkill /PID PORT_NUMBER /F

(No colon this time)

ISSUE 7: Communications link failure while running images


Solution : Add environment variables MYSQL_USER,MYSQL_DATABASE, MYSQL_PASSWORD 

ISSUE 8: Git Ci not getting connected to mysql db

Solution:Add useSSL=false&allowPublicKeyRetrieval=true to url of the environment variable URL of db.
