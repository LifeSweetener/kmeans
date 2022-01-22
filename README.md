# kmeans
 <p>Realization of clustering algorithm "kmeans" on Java</p>

<h2>Цель работы</h2>
<p>Реализовать кластеризацию методами <b>k-means</b> и <b>fuzzy c-means</b>. Набор данных выбирается самостоятельно. Возможен выбор популярного датасета “Ирисы Фишера”.</p>

<h2>Теоретическое введение</h2>
<p align="justify"><b>[4]</b> С развитием сети Интернет получили развитие вопросы построения распределенных баз данных, создания распределенных глобальных информационных систем. Многократно возросла интенсивность формирования и архивирования различных данных, за которыми следовало развитие масштабируемых программно-аппаратных комплексов, дорогостоящих мощных и недорогих пользовательских компьютеров и накопителей данных.</p>
<p align="justify">Все это способствовало всплеску развития индустрии ИКТ и сделало огромное количество баз данных доступными для хранения разнородной информации в значительных объемах и управления транзакциями в них. При этом все больше возникала <b>потребность анализа имеющихся данных</b> в разновременном аспекте, с возможностью построения произвольных запросов, при условии обработки сверхбольших объемов данных, полученных, в том числе, из различных регистрирующих БД. Использование для этих задач традиционных регистрирующих систем и БД крайне затруднительно. Например, в регистрирующей системе информация актуальна исключительно на момент обращения к БД, а в следующий момент времени по тому же запросу можно ожидать другой результат. Интерфейс таких систем рассчитан на проведение определенных стандартизованных операций и возможности получения результатов на нерегламентированный произвольный запрос ограничены. Возможности обработки больших массивов данных также могут быть ограничены вследствие ориентации СУБД на нормализованные данные, характерные для стандартных реляционных регистрирующих БД.</p>
<p align="justify">Ответом на возникшую потребность стало появление новой технологии организации баз данных — технологии <b>Хранилищ Данных</b> (англ. «Data Warehouse»), предполагающей некоторую предварительную обработку данных и их интеграцию, а также онлайновую аналитическую обработку (англ. «On-Line Analytical Processing», OLAP).</p>
<p>Несмотря на очевидную пользу такого инструмента анализа данных, он ориентирован на хорошо нормализованные табличные данные и не предполагает использование целого ряда дополнительного аналитического инструментария типа классификации, кластеризации, регрессионного анализа, моделирования, прогнозирования и интерпретации многомерных данных и т.п.</p>
<p align="justify">Таким образом, сегодня наблюдается высокий уровень развития масштабируемой аппаратно-программной ИКТ инфраструктуры, позволяющей увеличивать и без того значительные архивы данных. Имеется достаточно существенный задел в области компьютерных наук и информационных технологий, разработаны теория и прикладные аспекты теории вероятности и математической статистики. Однако при этом следует признать, что присутствует заметный избыток данных при дефиците информации и знаний. Быстро растущие объемы накопленных и пополняемых (автоматически, а не людьми — как это было когда-то) архивов данных пока существенно превышают способности человека в их практически полезной обработке. Для обострения этого тезиса иногда говорят, что «большие базы данных стали могилами, которые редко посещаются». Как следствие, важные решения порой принимаются не на основе аналитических выводов из информативных БД, а на основе интуиции человека, не имеющего подходящих инструментов для извлечения полезных знаний из имеющихся огромных объемов данных.</p>
<p align="justify">Поэтому в последние годы стремительное развитие получила область <b>Data Mining</b> (в отечественной литературе наиболее используемая аналогия — интеллектуальный анализ Данных, ИАД), направленная на поиск и разработку методов извлечения из имеющихся данных знаний, позволяющих принимать на их основе конкретные, в высокой степени обоснованные, практически полезные управленческие решения <b>[4]</b>.
</p>
<h2>Ход работы</h2>
<p align="justify">Для написания кода была использована IDE IntelliJ IDEA. Язык программирования был выбран Java. Помимо стандартных Java-библиотек, использовалась также библиотека для построения графиков и диаграмм «JFreeChart» автора Дэвида Гилберта (David Gilbert; он же jfree) <b>[3]</b>.</p>
<p align="justify">Как указано, в презентации, которую сделали мои одногруппницы, есть строго определённые шаги при выполнении алгоритмов «k-means» и «fuzzy c-means» <b>[1,2]</b>. Их вы, дорогой читатель, должны придерживаться.</p>
<p align="justify">Структура кода как реализации первого из этих алгоритмов кластеризации содержит в себе три класса: Main, KMeans и Universe (от англ. «Вселенная»).</p>
<p align="justify">Группируются в этой работе звёзды в шесть заранее выбранных кластеров (т.е. групп) (см. рисунок 1).<br></p>
<table><tr><td><p align="center"><img src="img/1.jpg" alt="Наглядное изображение кластеров звёзд" border="1px" width="75%" height="90%" align="middle"></img><br><span>Рисунок 1 — Исследуемая предметная область</span></p></td></tr></table>
<p align="justify">Чем выше температура, тем у звезды цвет холоднее. И чем выше масса звезды, тем короче её жизненный цикл. Совсем массивные звёзды превращаются в нейтронные звёзды или чёрные дыры в конце своего существования.</p>
<p align="justify">Единицы обсуждаемых величин, хоть и показаны в килограммах и кельвинах (см. рисунок выше), но всё равно являются по большей части выдуманными и несоответствующими реальным значениям температуры и массы подобных светил. Но в целом, для этой работы сойдут и такие, очень-очень приближённые значения. Это сделано для удобства реализации кода.</p>
<p align="justify">Класс Universe описывает звёзды и используется в классе KMeans в процессе группировки (кластеризации). Класс Main по умолчанию начальная точка запуска всей программы. В Main рисуется также график при помощи модуля JFreeChart.</p>
<p align="justify">Начальная выборка звёзд задаётся в том же классе Main в самом начале (см. код ниже).</p>

<p align="left">
<code>public static void <b>main</b>(String[] args) {</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;String[] <b>stars</b> = new String[18];</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;stars[0] = "1000:1000.0;-1"; stars[1] = "2050:1400.0;-1"; stars[2] = "3000:2500.0;-1";</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;stars[3] = "2100:5600.0;-1"; stars[4] = "5050:6000.0;-1"; stars[5] = "3000:6900.0;-1";</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;stars[6] = "4500:11500.0;-1"; stars[7] = "5050:12000.0;-1"; stars[8] = "7000:10800.0;-1";</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;stars[9] = "18100:10350.0;-1"; stars[10] = "18800:11110.0;-1"; stars[11] = "16300:12000.0;-1";</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;stars[12] = "10000:7000.0;-1"; stars[13] = "13000:7300.0;-1"; stars[14] = "15500:7900.0;-1";</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;stars[15] = "13700:1500.0;-1"; stars[16] = "15000:2100.0;-1"; stars[17] = "16000:3700.0;-1";</code><br>
<code>}</code>
</p>
<p align="justify">В классе Universe есть массив строк stars, который и содержит в подобном формате описание всех звёзд (объектов). Массив выше (см. код 1) передаётся в качестве аргумента в конструктор класса Universe при его создании, и тем самым инициализируются все объекты Universe, над которыми и будет проводиться работа.</p>
<p align="justify">Этот Universe, как было сказано чуть раньше, передаётся следом в конструктор уже KMeans — класс, который содержит в себе методы алгоритма кластеризации «k-means» [2,1]:</p>
<p align="center"><code>KMeans kmeans = <b>new KMeans(new Universe(stars))</b>;</code></p>
<p align="justify">Далее идёт вывод результатов в консоль и в окно модуля JFreeChart:</p>

<table background="yellow"><tr><td><p align="left">
<code>XYSeries series = new XYSeries("Universe");</code><br><br>

<code>int[] temperatures = kmeans.sample.getTemp();</code><br>
<code>float[] masses = kmeans.sample.getMass();</code><br><br>

<code>System.out.println(kmeans.sample);</code><br><br>

<code>kmeans.run();</code><br><br>

<code>System.out.println(kmeans.sample);</code><br><br>

<code>for(int i = 0; i < kmeans.sample.count(); ++i) {</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;series.add(masses[i], temperatures[i]);</code><br>
<code>}</code><br><br>

<code>XYDataset xyDataset = new XYSeriesCollection(series);</code><br><br>

<code>JFreeChart chart = ChartFactory</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;.createScatterPlot("Stars", "MASS", "TEMPERATURE",</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;xyDataset,</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;PlotOrientation.VERTICAL,</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;true, true, true);</code><br><br>

<code>JFrame frame =</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;new JFrame("Clusterization");</code><br>
<code>frame.getContentPane()</code><br>
<code>&nbsp;&nbsp;&nbsp;&nbsp;.add(new ChartPanel(chart));</code><br><br>

<code>frame.setSize(400,300);</code><br>
<code>frame.show();</code>
 </p></td></tr></table>
