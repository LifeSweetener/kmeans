# kmeans
 <p>Realization of clustering algorithm "kmeans" on Java</p>

<h2>Цель работы</h2>
<p>Реализовать кластеризацию методами <b>k-means</b> и <b>fuzzy c-means</b>. Набор данных выбирается самостоятельно. Возможен выбор популярного датасета “Ирисы Фишера”.</p>

<h2>Теоретическое введение</h2>
<p><b>[4]</b> С развитием сети Интернет получили развитие вопросы построения распределенных баз данных, создания распределенных глобальных информационных систем. Многократно возросла интенсивность формирования и архивирования различных данных, за которыми следовало развитие масштабируемых программно-аппаратных комплексов, дорогостоящих мощных и недорогих пользовательских компьютеров и накопителей данных.</p>
<p>Все это способствовало всплеску развития индустрии ИКТ и сделало огромное количество баз данных доступными для хранения разнородной информации в значительных объемах и управления транзакциями в них. При этом все больше возникала <b>потребность анализа имеющихся данных</b> в разновременном аспекте, с возможностью построения произвольных запросов, при условии обработки сверхбольших объемов данных, полученных, в том числе, из различных регистрирующих БД. Использование для этих задач традиционных регистрирующих систем и БД крайне затруднительно. Например, в регистрирующей системе информация актуальна исключительно на момент обращения к БД, а в следующий момент времени по тому же запросу можно ожидать другой результат. Интерфейс таких систем рассчитан на проведение определенных стандартизованных операций и возможности получения результатов на нерегламентированный произвольный запрос ограничены. Возможности обработки больших массивов данных также могут быть ограничены вследствие ориентации СУБД на нормализованные данные, характерные для стандартных реляционных регистрирующих БД.</p>
<p>Ответом на возникшую потребность стало появление новой технологии организации баз данных — технологии <b>Хранилищ Данных</b> (англ. «Data Warehouse»), предполагающей некоторую предварительную обработку данных и их интеграцию, а также онлайновую аналитическую обработку (англ. «On-Line Analytical Processing», OLAP).</p>
<p>Несмотря на очевидную пользу такого инструмента анализа данных, он ориентирован на хорошо нормализованные табличные данные и не предполагает использование целого ряда дополнительного аналитического инструментария типа классификации, кластеризации, регрессионного анализа, моделирования, прогнозирования и интерпретации многомерных данных и т.п.</p>
<p>Таким образом, сегодня наблюдается высокий уровень развития масштабируемой аппаратно-программной ИКТ инфраструктуры, позволяющей увеличивать и без того значительные архивы данных. Имеется достаточно существенный задел в области компьютерных наук и информационных технологий, разработаны теория и прикладные аспекты теории вероятности и математической статистики. Однако при этом следует признать, что присутствует заметный избыток данных при дефиците информации и знаний. Быстро растущие объемы накопленных и пополняемых (автоматически, а не людьми — как это было когда-то) архивов данных пока существенно превышают способности человека в их практически полезной обработке. Для обострения этого тезиса иногда говорят, что «большие базы данных стали могилами, которые редко посещаются». Как следствие, важные решения порой принимаются не на основе аналитических выводов из информативных БД, а на основе интуиции человека, не имеющего подходящих инструментов для извлечения полезных знаний из имеющихся огромных объемов данных.</p>
<p>Поэтому в последние годы стремительное развитие получила область <b>Data Mining</b> (в отечественной литературе наиболее используемая аналогия — интеллектуальный анализ Данных, ИАД), направленная на поиск и разработку методов извлечения из имеющихся данных знаний, позволяющих принимать на их основе конкретные, в высокой степени обоснованные, практически полезные управленческие решения <b>[4]</b>.
</p>
<h2>Ход работы</h2>
<p>Для написания кода была использована IDE IntelliJ IDEA. Язык программирования был выбран Java. Помимо стандартных Java-библиотек, использовалась также библиотека для построения графиков и диаграмм «JFreeChart» автора Дэвида Гилберта (David Gilbert; он же jfree) <b>[3]</b>.</p>
<p>Как указано, в презентации, которую сделали мои одногруппницы, есть строго определённые шаги при выполнении алгоритмов «k-means» и «fuzzy c-means» <b>[1,2]</b>. Их вы, дорогой читатель, должны придерживаться.</p>
<p>Структура кода как реализации первого из этих алгоритмов кластеризации содержит в себе три класса: Main, KMeans и Universe (от англ. «Вселенная»).</p>
<p>Группируются в этой работе звёзды в шесть заранее выбранных кластеров (т.е. групп) (см. рисунок 1).</p>

