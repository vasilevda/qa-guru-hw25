# Автоматизация тестирования ресусров магазина OZON
![](https://cdn1.ozone.ru/s3/cms/53/t73/wc200/logo-logo-ozon-blue-png_2.png)  


## Оглавление
+ [Введение](#Description)
+ [Технологии и инструменты](#Technology)
+ [Запуск тестов в Jenkins](#Jenkins)
    + [Параметры сборки System.property](#SystemProperty)
    + [Параметры сборки properties документа](#ParamProperties)
        + [Оформление документа Configure.properties](#Properties)
        + [Варинты документа Configure.properties для запуска тестов](#TypeDocumentProperties)
+ [Отчет о результатах тестирования в Allure Report](#AllureReport)
+ [Интеграция с Allure TestOps](#AllureTestOps)
+ [Интеграция с Jira](#Jira)
+ [Результаты выполнения тестов](#Results)



## <a name="Description">Введение</a>
- Все представленные тесты были выполнены в рамках подвидения итогов прохождения курсов на портале QA GURU;
- В некоторых тестах **СПЕЦИАЛЬНО** :D были допущены ошибки;



# <a name="Technology">Технологии и инструменты</a>
<p  align="center">
  <code><img width="5%" title="IntelliJ IDEA" src="images/IDEA-logo.svg"></code>
  <code><img width="5%" title="Java" src="images/java-logo.svg"></code>
  <code><img width="5%" title="Selenide" src="images/selenide-logo.svg"></code>
  <code><img width="5%" title="REST-Assured" src="images/rest-assured-logo.svg"></code>
  <code><img width="5%" title="Selenoid" src="images/selenoid-logo.svg"></code>
  <code><img width="5%" title="Gradle" src="images/gradle-logo.svg "></code>
  <code><img width="5%" title="JUnit5" src="images/junit5-logo.svg"></code>
  <code><img width="5%" title="Allure Report" src="images/allure-Report-logo.svg"></code>
  <code><img width="5%" title="Allure TestOps" src="images/allure-ee-logo.svg"></code>
  <code><img width="5%" title="Github" src="images/git-logo.svg"></code>
  <code><img width="5%" title="Jenkins" src="images/jenkins-logo.svg"></code>
  <code><img width="5%" title="Jira" src="images/jira-logo.svg"></code>
  <code><img width="5%" title="Telegram" src="images/Telegram.svg"></code>
</p>

В данном проекте автотесты написаны на **Java** с использованием фреймворка **Selenide**.
Для сборки проекта используется **Gradle**.  
**JUnit 5** используется как фреймворк для модульного тестирования.
Запуск тестов выполняется из **Jenkins**.
**Selenoid** используется для запуска браузеров в контейнерах **Docker**.
**Allure Report, Telegram Bot** используются для визуализации результатов тестирования.



# <a name="Jenkins">Запуск тестов в [Jenkins](https://jenkins.autotests.cloud/job/)</a>

## <a name="SystemProperty">Параметры сборки System.property</a>
```bash
gradle clean test 
-Dtag=${tag}
```

```mermaid
graph LR
A[TAG] --> B[UI]
A --> D[Mobile]
A --> E[API]
```

## <a name="ParamProperties">Параметры сборки properties документа</a>
Для запуска тестов, нобходимо заполнить документ **Configure.properties** определенными данными, от которых будет зависеть выполнение разных модулей проекта.

### <a name="Properties">Оформление документа Configure.properties</a>

Структура файла .properties:
```
  https.url=
  https.curl=
  device.name=
  https.browser=
  device.user=
  device.key=
```
Примечание к Configure.properties:
>- *https.url* - унифицированный указатель ресурса
>- *https.curl* - служебная программа командной строки, позволяющая взаимодействовать с множеством различных серверов по множеству различных протоколов
>- *device.name* - имя ресурса\девайса\инструмента являющееся важным ключём позволяющее запускать определенные тесты
>- *device.browser* - браузер, в котором будут выполняться тесты (по умолчанию chrome)
>- *device.user* - логин для авторизации
>- *device.key* - ключ\пароль для авторизации

### <a name="TypeDocumentProperties">Варинты документа Configure.properties для запуска тестов</a>

```mermaid
graph LR
A[Properties] --> B[UI]
A[Properties] --> C[Mobile]
C --> D[Browserstack]
C --> E[Selenide]
C --> F[Emulator]
C --> G[Real]
```

<details>
    <summary><h4>Configure.properties для UI</h4></summary>
    
        https.url=http://selenoid:4444/wd/hub
        https.curl=https://${server.host.login}:${server.host.password}@selenoid.autotests.cloud/wd/hub
        device.name=UI
        https.browser=
        device.user=user1
        device.key=user1
   
</details>
<details>
    <summary><h4>Configure.properties для Android</h4></summary>
    
*   <details>
        <summary><h4>Browserstack</h4></summary>

            https.url=http://hub.browserstack.com/wd/hub
            https.curl=https://${device.user}:${device.key}@api-cloud.browserstack.com/app-automate/upload
            device.name=browserstack
            https.browser=
            device.user=bsuser_CVEMKg
            device.key=bqsfjY6VFvsxvhETqybW
    </details>
*   <details>
        <summary><h4>Selenide</h4></summary>

            https.url=http://hub.browserstack.com/wd/hub
            https.curl=https://${device.user}:${device.key}@api-cloud.browserstack.com/app-automate/upload
            device.name=browserstack
            https.browser=
            device.user=bsuser_CVEMKg
            device.key=bqsfjY6VFvsxvhETqybW
    </details>
*   <details>
        <summary><h4>Emulator</h4></summary>

            https.url=http://localhost:4723/wd/hub
            https.curl=
            device.name=Emulation
            https.browser=
            device.user=
            device.key=
    </details>
*   <details>
        <summary><h4>Real</h4></summary>

            https.url=http://localhost:4723/wd/hub
            https.curl=
            device.name=Real
            https.browser=
            device.user=
            device.key=
    </details>
</details>

# <a name="AllureReport">Отчет о результатах тестирования в [Allure Report](https://jenkins.autotests.cloud/job)</a>

#### Общая информация
Главная страница Allure-отчета содержит следующие информационные блоки:

>- <code><strong>*ALLURE REPORT*</strong></code> - отображает дату и время прохождения теста, общее количество прогнанных кейсов, а также диаграмму с указанием процента и количества успешных, упавших и сломавшихся в процессе выполнения тестов
>- <code><strong>*TREND*</strong></code> - отображает тренд прохождения тестов от сборки к сборке
>- <code><strong>*SUITES*</strong></code> - отображает распределение результатов тестов по тестовым наборам
>- <code><strong>*CATEGORIES*</strong></code> - отображает распределение неуспешно прошедших тестов по видам дефектов
<p align="center">
  <img src="images/Allure Report.png" alt="Allure Report" width="650">
</p>

### Список тестов c описанием шагов и визуализацией результатов
На данной странице представляется стандартное распределение выполнявшихся тестов по тестовым наборам или классам, в
которых находятся тестовые методы.

<p align="center">
  <img src="images/Allure Report steps.png" alt="Allure Report" width="650">
</p>



# <a name="AllureTestOps">Интеграция с [Allure TestOps](https://allure.autotests.cloud/)</a>



# <a name="Jira">Интеграция с [Jira](https://jira.autotests.cloud/)</a>



# <a name="Results">Результаты выполнения тестов</a>

### Пример запуска теста в Selenoid
<p align="center">
  <img src="images/video.gif" alt="video" width="650">
</p>

### Уведомления в Telegram
<p align="center">
  <a href="http://www.pidor.com/"><img src="images/tlgrm.png" alt="Telegram" width="650"></a>
</p>
