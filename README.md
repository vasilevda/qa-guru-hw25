# Автоматизация тестирования ресусров магазина OZON
![](https://cdn1.ozone.ru/s3/cms/53/t73/wc200/logo-logo-ozon-blue-png_2.png)  


## Оглавление
+ [Введение](#Description)
+ [Технологии и инструменты](#Technology)
+ [Запуск тестов в Jenkins](#Jenkins)
    + [Параметры для сборки](#ParametersRun)
    + [Оформление документа Configure.properties](#Properties)
    + Item B 3
+ [Отчет о результатах тестирования в AllureReport](#AllureReport)

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

Для запуска тестов, нобходимо заполнить документ **Configure.properties** определенными данными, от которых будет зависеть выполнение разных модулей проекта.

## <a name="ParametersRun">Параметры для сборки</a>

### <a name="Properties">Оформление документа Configure.properties</a>
```XML
  https.url=
  https.curl=
  device.name=
  https.browser=
  device.user=
  device.key=
```



# <a name="AllureReport">Отчет о результатах тестирования в [Allure Report](https://jenkins.autotests.cloud/job)</a>
## Общая информация

*Главная страница Allure-отчета содержит следующие информационные блоки:*

>- [x] <code><strong>*ALLURE REPORT*</strong></code> - отображает дату и время прохождения теста, общее количество прогнанных кейсов, а также диаграмму с указанием процента и количества успешных, упавших и сломавшихся в процессе выполнения тестов
>- [x] <code><strong>*TREND*</strong></code> - отображает тренд прохождения тестов от сборки к сборке
>- [x] <code><strong>*SUITES*</strong></code> - отображает распределение результатов тестов по тестовым наборам
>- [x] <code><strong>*CATEGORIES*</strong></code> - отображает распределение неуспешно прошедших тестов по видам дефектов
<p align="center">
  <img src="images/Allure Report.png" alt="Allure Report" width="900">
</p>

### Список тестов c описанием шагов и визуализацией результатов

*На данной странице представляется стандартное распределение выполнявшихся тестов по тестовым наборам или классам, в
которых находятся тестовые методы.*

<p align="center">
  <img src="images/Allure Report steps.png" alt="Allure Report" width="900">
</p>

## Пример запуска теста в Selenoid
<p align="center">
  <img src="images/video.gif" alt="video" width="1000">
</p>

## Уведомления в Telegram
<p align="center">
  <a href="http://www.pidor.com/"><img src="images/tlgrm.png" alt="Telegram" width="440"></a>
</p>

