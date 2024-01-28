![[_67210929-3207-407c-8654-5ec03ef9c6a8.jpg]]
<div style="text-align: right;">
Joan Llompart Socias
</div> 
<div style="text-align: right;">
2n DAW Es Liceu
</div>
<div style="text-align: right;">
Entorn Servidor
</div>

------
## Índex

1. [Objectius]
2. [Descripció de l’estructura de el programa]
3. [Descripció detallada de cada procediment]
4. [Tecnologies utilitzades]

-----------
>[!IMPORTANT]
	>Per probar l'Aplicació Web tots els usuaris tenen la mateixa *password* que es ***hola1
	>Usuari de proba: pere@gmail.com
	
	
## 1. Objectius

### 1.1 Objectius d'aprenentatge:

Els objectius son el desplegament d'una Aplicacio Web conectada a un servidor web fent us de Spring Boot i Maven.

- El acces a dades en aquesta versio 2.0 se encarregara un contenidor docker que te instalat un servidor apache mysql amb **phpmyadmin.**

- Seguir el patro de diseny d'**arquitectura multicapa amb Spring Boot** (**Presentacio, Negoci, Accés a Dades**) que a continuació veurem com funciona.

1. **Capa de Presentació (Controller i DTO):** Aquesta capa es dedica a interactuar amb l'usuari i mostrar la informació. A Spring Boot, els Controllers reben peticions del client i retornen objectes de sortida (DTOs). Els DTOs són objectes senzills que transporten dades entre el client i el servidor en una única invocació. Es molt important que no se utilitzin a la capa d'acces a dades.

2. **Capa de Negoci (Service i Entity):** Aquí s'aplica la lògica de l'aplicació i les regles de negoci. Els Services es comuniquen amb els Controllers i els Repositories. Poden rebre o retornar DTOs o convertir-los en objectes de domini (Entities). Les Entities representen les entitats del model de dades i poden ser anotades amb JPA per facilitar la seva persistència a la base de dades.

3. **Capa d'Accés a Dades (Repository):** Aquesta capa interactua amb l'emmagatzematge de dades (com bases de dades) i realitza operacions de consulta, inserció, actualització i esborrat. Els Repositories, que estenen interfícies de Spring Data, gestionen les Entities. Abstreu la tecnologia d'accés a dades i faciliten les proves unitàries.

Aquest patró d'arquitectura ajuda a mantenir un codi més organitzat, separant les preocupacions en diferents capes i facilitant el manteniment i l'escalabilitat del projecte a Spring Boot, com podem observar la idea es la molt semblant a la primera practica Drawing adaptada a
un model mes acord per el desenvolupament d'aplicacions web amb **Spring boot**.

- Els dos aspectes mes importants amb la segona versió es utilitzar un **framework** que en aquesta practica hem fet ús de **Spring Boot** i el segon canvi mes important es l'utilització de la **api de Fetch** 
	
 - **Spring Boot** és un framework d'aplicacions Java que es construeix sobre l'ecosistema de Spring. Està dissenyat per simplificar el procés de configuració i desenvolupament d'aplicacions Java, especialment per a aplicacions web i serveis. Proporciona un conjunt d'eines i convencions que permeten crear aplicacions de manera més ràpida i amb menys configuració manual.

	Algunes de les **característiques** clau de **Spring Boot** són:

	1. **Configuració Automàtica:** Spring Boot intenta configurar automàticament tantes coses com sigui possible basant-se en les dependències afegides al projecte. Ofereix configuracions predeterminades sensates per a molts aspectes, la qual cosa redueix la quantitat de configuració explícita necessària.
	2. **Dependencies Starter:** Proporciona "starters" (iniciadors) que són conjunts de dependències preconfigurades per a diferents tipus d'aplicacions (per exemple, aplicacions web, accés a bases de dades, seguretat, etc.). Això simplifica la configuració i redueix la complexitat afegint només el necessari per a un conjunt particular de característiques.
	3. **Embedded Server :** Inclou un servidor inter ("**Embedded**" ) com ( Tomcat, Jetty o Undertow) per executar l'aplicació, la qual cosa significa que no és necessari empaquetar l'aplicació amb un servidor extern. Això simplifica la distribució i execució de l'aplicació.
    

	Ara bé, en quant a la diferència entre Spring Boot i l'ús directe de Servlets en Java a la primera versio de l'aplicació:

	- **Servlets:** Els Servlets són una tecnologia Java que permet manejar sol·licituds HTTP en el costat del servidor. Són la base del desenvolupament web en Java i proporcionen una interfície per manejar les sol·licituds, generar respostes i realitzar accions relacionades amb el servidor.
    
	- **Spring Boot:** Spring Boot, d'altra banda, és un marc de treball de nivell superior que simplifica el desenvolupament d'aplicacions web en Java. Tot i que utilitza Servlets internament (ja que els contenidors d'aplicacions web Java com Tomcat o Jetty es basen en Servlets), aminora gran part de la complexitat de treballar directament amb ells. Proporciona una manera més ràpida i convenient de configurar, desenvolupar i executar aplicacions Java, alleujant la càrrega de configuració manual que es requeriria en treballar només amb Servlets. A continuació veurem lo facil que resulta configurar un servidor amb **Spring Boot**. 

	- Com podem veure a l'imatge inferior basta amb anar la Web de https://start.spring.io i seleccionar totes les dependencies que necesitarem i una vegada hem acabat, feim click a generate i automaticament generara un Jar que copiarem a el directori de el projecte i ja tendrem les nostres dependencies preparades per comensar la aplicacio web.
	![[Pasted image 20231122201008.png]]
- **Api fetch** :  és una API moderna de JavaScript per fer sol·licituds de xarxa (HTTP) des del navegador. Permet interactuar amb recursos (com arxius JSON, imatges, dades del servidor, etc.) de manera asíncrona, la qual cosa significa que pots fer sol·licituds i manejar les respostes sense bloquejar l'execució del codi.

L'API **fetch** té diversos avantatges i alguns inconvenients que és important tenir en compte en el seu ús:

 -  Avantatges de **fetch**:

1. **Sintaxi senzilla i moderna:** **fetch** utilitza una sintaxi més senzilla i basada en promeses, la qual cosa la fa més fàcil d'entendre i utilitzar en comparació amb altres formes de fer sol·licituds AJAX a JavaScript.
    
2. **Estàndard modern:** És una API moderna que forma part dels estàndards web, per la qual cosa està ben suportada en navegadors moderns.
    
3. **Ús de Promeses:** Retorna una Promesa, la qual cosa facilita l'escriptura de codi asíncron i el maneig de les respostes de manera més eficient.
    
4. **Manipulació flexible de respostes:** Permet manipular diferents tipus de respostes (JSON, text, blob, etc.) utilitzant mètodes com *.json(), .text(), .blob().*
    
5. **Suport per capçaleres i opcions de sol·licitud:** Permet configurar fàcilment capçaleres HTTP, definir el mètode de sol·licitud, entre d'altres paràmetres, a través de les opcions de configuració.
    

- **Inconvenients de fetch:**

1. **No gestiona directament errors de xarxa:** *fetch* només rebutja la Promesa en cas d'error de xarxa si la sol·licitud no es pot realitzar (per exemple, per problemes de connectivitat). Els errors HTTP no es consideren errors de xarxa directament, per la qual cosa cal fer comprovacions manuals de codi d'estat HTTP per determinar errors específics.
    
2. **Compatibilitat amb navegadors antics:** No és compatible amb navegadors antics, per la qual cosa si es necessita suport per a versions més antigues, pot requerir l'ús de polyfills o llibreries addicionals per garantir la compatibilitat.
    

###### Usos comuns de fetch:

1. **Obtenció de dades d'API:** S'utilitza per fer sol·licituds a servidors i obtenir dades JSON, text, imatges o altres recursos.
    
2. **Actualització de contingut dinàmic:** Permet actualitzar contingut en una pàgina web sense necessitat de recarregar-la completament, la qual cosa és útil per a aplicacions d'una sola pàgina (SPA), que no es el cas, l'utilitzam per modificacions de el Canvas majoritàriament.
    
3. **Enviament de dades al servidor:** S'utilitza per enviar dades a un servidor utilitzant mètodes com POST, PUT o DELETE.
    
4. **Integració amb serveis web:** És comú en el desenvolupament d'aplicacions web que es comuniquen amb serveis web externs per obtenir o enviar dades.
    

fetch és una eina potent i versàtil per fer sol·licituds de xarxa a JavaScript, però és important tenir en compte la seva compatibilitat amb navegadors i com gestiona els errors per aprofitar al màxim els seus avantatges i mitigar els seus inconvenients.

- En aquesta pràctica utilitzam fetch sobre tot per guardar automaticament els **Canvas** cuant el estam modificant i tambe com veurem mes endevant per intentar garantitzar que es guardi sempre el canvas per molt que el usuari surti de la pagina.

-----

### 1.2 Objectius del programa:

L'objectiu de el programa es realitzar una Aplicacio Web que combina tant Front-end com Back-end que ens permeti realitzar dibuixos en un Canvas.

- L'aplicació permet a els usuaris registrarse i fer login.

>[!NOTE]
	> L'usuari per fer us de cualsevol funcionalitat ha de estar registrat i fer login amb el seu usuari, de el contrari no pot fer us de l'aplicació.

 
- L'usuari ha de poder inserir 4 tipus de figures  _(Cercle, Triangle ,Estrella de set puntes, Quadrat )_ que a part de la eleccio de la figura podra elegir :
	- Grandaria de la figura.
	- Color
	- Si esta pintada per dedins o no.
- L'usuari ha de poder realitzar dibuixos a ma alçada en el canvas i es poden elegir 2 atributs de traç:
	- Gruixada de el traç.
	- Color de el traç.
- L'usuari pot realitzar en un mateix canvas les dues accions anteriors.
- Una vegada realitzat el canvas el usuari tendra la opcio de crear un nom a el seu Canvas, si desideix no asignarli un nom el servidor li asigna un nom aleatori.
- El canvas es guardara en el servidor una vegada el usuari el vulgui enviar.
- En el moment de cracio de el Canvas disposam de una llista dinamica que ens permet veure els elements que tenim per el tipus de objecte que hem afegit a el canvas i tambe disposam de un boto de eliminar cada objecte en concret.Es a dir, si dibuixam una figura "triangle" i la volem cambiar per un "cuadrat" a la llista feim click a la "x" de la figura que volem eliminar i se eliminara, despres podrem afegir el cuadrat.

- **L'usuari podra veure i eliminar els seus propis canvas en la seccio MisCanvas.**

- L'usuari tambe podra veure els canvas de els altres usuaris pero nomes podra eliminar el seu propi.

- Les imatges es guarden al servidor automàticament cada vegada que s’afegeix o
	s’elimina un objecte (o bé cada cert temps, per exemple, cada minut).

- Cada vegada que es guarda una imatge, s’emmagatzema també la versió anterior,
	de manera que tindrem un historial dels canvis que hem fet. Aquestes versions s’han
	de poder consultar quan es veu o s’edita la imatge. Cada imatge té sempre una
	versió “actual”, que és la darrera de totes.

- **L’usuari ha de poder recuperar qualsevol versió anterior de la imatge.** Cada versió
	tindrà una marca de temps (timestamp) associada.

- **Quan s’esborra una imatge, també s’esborren totes les seves versions anteriors.**

- Quan **l’usuari esborra una imatge,** aquesta no s’eliminar de la base de dades, sinó
	**que va a la paperera de l’usuari.** L’usuari pot decidir eliminar definitivament la imatge
	si l’esborra també de la paperera.
	
- La informació en el servidor s’emmagatzemarà a una base de dades mysql. Així,
	cada vegada que s’inicii l’aplicació al servidor no perdem les dades.
	
- Les imatges es **podran compartir en mode “lectura” i “escriptura”.** És a dir, un usuari
	només podrà veure les imatges que siguin “públiques” (compartides a tothom) o
	s’hagin compartit directament amb ell.
	
- Hi haurà un **sistema de permisos (lectura/escriptura) sobre cada imatge compartida**.
	- Els usuaris que tinguin permís d’escriptura podran esborrar i modificar la imatge.
- **Només podran compartir les imatges els “propietaris” de les imatges.** És a dir, un
	usuari no podrà compartir amb altres usuaris una imatge que no sigui seva.
- **Un usuari ha de poder fer còpies de les imatges** (no s’inclou l’historial) d’una imatge
	qualsevol (sigui seva o compartida).
- **Per defecte, una imatge creada per un usuari és privada** (és a dir, només visible i
	editable pel propi usuari).

	L’aplicació ha d’estar programada emprant spring-web, tal com s’ha explicat a classe. La
	capa de persistència (base de dades) es farà mitjançant JDBCTemplate.


>[!IMPORTANT]
	> L'usuari nomes pot accedir a els Canvas que son seus o que el propietari li ha otorgat permis ja sigui de Lectura o de Escritura,si te permis de escritura automaticament en te de lectura.
	
----

## 2. Descripció de l’estructura de el programa


>[!NOTE]
	>En Spring Boot es fonamental utilitzar els anomenats `@Bean` ja que es la base d'aquest **framework** per poder detectar els diferents elements de l'aplicació.


Les mes importants per aquesta practica són  `@Controller`, `@Service`, `@Repository`, `@Autowired`, `@Component`, entre altres.

Aquestes anotacions són crítiques per a la funcionalitat de Spring Boot ja que:

1. **Defineixen i marquen els components:** `@Controller`, `@Service`, `@Repository` i `@Component` s'utilitzen per definir i marcar els diferents tipus de components de l'aplicació, com els controladors, serveis, repositoris i components generals, respectivament.

2. **Gestionen la Injecció de Dependències:** Anotacions com `@Autowired` permeten la injecció de dependències de forma automàtica, facilitant la connexió entre els diferents components de l'aplicació.

3. **Configuració i Personalització:** Anotacions com `@Configuration`, `@Bean`, `@Value` s'utilitzen per configurar i personalitzar el comportament dels components i l'aplicació en conjunt.

4. **Maneig de Transaccions, Seguretat, Excepcions, etc.:** Spring Boot també ofereix anotacions per gestionar la transaccionalitat (`@Transactional`), la seguretat (`@Secured`, `@PreAuthorize`, etc.), el maneig d'excepcions (`@ExceptionHandler`) i altres aspectes crítics d'una aplicació.

Aquestes anotacions són fonamentals per al funcionament i la configuració de l'aplicació a Spring Boot, ja que defineixen la seva estructura, el seu comportament i faciliten l'escriptura de codi net i mantenible mitjançant la inversió de control i la injecció de dependències.

---


### L'aplicació esta dividida en dos pakages principals  `java` i `resources`

### Package Java:

#### Controller:

- **Controller**: Connecta la View amb el Model i gestiona les interaccions de l'usuari.
  - Processa les peticions de l'usuari i actua com a intermediari entre la View i el Model.
  - Conté la lògica de control, coordina les peticions de l'usuari i decideix com gestionar-les.
  - En les aplicacions web, pot rebre les peticions HTTP, accedir al Model, i enviar la resposta apropiada a la View.


Al utilitzar Spring Boot i la seva estructura de capes amb conceptes com Controllers, DTOs, Entities, Repositories, Services i maneig d'excepcions, hi ha certs aspectes clau a considerar:

- **Anotacions i Mapeig d'Endpoints:** Els Controllers a Spring Boot es defineixen amb l'anotació `@Controller` o `@RestController`. És essencial mapejar correctament les rutes dels endpoints utilitzant anotacions com `@RequestMapping`, `@GetMapping`, `@PostMapping`, etc.

- **Maneig d'Entrada/Sortida:** Els Controllers reben les sol·licituds del client i retornen respostes. És crucial validar i processar correctament l'entrada de dades provinent de les sol·licituds i formatar adequadament les respostes.


#### DTOs (Data Transfer Objects):

- **Transferència de Dades:** Els DTOs són objectes plans que transporten dades entre el client i el servidor. S'han de dissenyar per optimitzar la transferència de dades i evitar l'exposició innecessària de l'estructura interna del sistema.

- **Mapeig a Entitats:** Es poden utilitzar per convertir dades d'Entitats (Entities) a un format més adequat per a la resposta al client i viceversa.

#### Entities:

- **Model de Dades:** Representen les entitats del model de dades. S'han de dissenyar amb atenció a l'estructura de la base de dades i anotades adequadament per a la persistència amb tecnologies com JPA, utilitzant anotacions com `@Entity`, `@Id`, `@Column`, etc.

- **Relacions entre Entitats:** Si existeixen relacions entre entitats (com relacions un a un, un a molts, molts a molts), és essencial definir i manejar correctament aquestes relacions a les entitats.

#### Repositories:

- **Accés a Dades:** Els Repositories són interfícies que estenen les interfícies de Spring Data i s'utilitzen per interactuar amb la base de dades. S'han de definir correctament els mètodes necessaris per a realitzar operacions CRUD (Create, Read, Update, Delete) i consultes personalitzades si és necessari.

- **Abstracció de la Capa de Dades:** Els Repositories ajuden a abstraure la lògica d'accés a dades, permetent una fàcil gestió de la persistència i facilitant les proves unitàries.

- Com a la primera versio ens referiem com a *DAO* el **Repositories** és el encarregat de tot el que es acces a dades, en el nostre cas una  base de dades amb MySQL pero tambe podria ser cualsevol altre com per exemple MongoDB,PostgreSQL ,etc. 
		**- El model DAO consisteix en dues parts :**  
			- **Interficie Repo:** Se declaren tots els metodes de acces a les dades.
			- **Classe RepoImpl** que conte la implementació de tots els metodes de la interficie de Repo i estableix la conexio amb la base de dades.
	
	- El sentit de la separacio de **Repo** en una interficie i amb una clase que implementa els propis metodes de la iterficie es simplement perque ens permet tenir la ventatge de escalar el nostre codi mes facilment o de mantenir en un futur, es a dir, separar els metodes de acces a dades de la logica de la implementacio de acces a la base de dades, es que si en un futur voler cambiar de base de dades sera molt mes facil ja que no rompra cap part de el codi perque podrem seguir utilitzant la mateixa interficie amb els metodes necesaris declarats i nomes tendrem que realitzar la implementacio en el model de base de dades que tendrem.Inclus ens permet tenir dues bases de dades per exemple una amb el motor de SQL com Mysql i l'altre amb NoSQL com MongoDB.


#### Services:

- **Lògica de Negoci:** Les capes de Service contenen la lògica de negoci de l'aplicació. S'han d'implementar amb mètodes que gestionin la lògica de l'aplicació, utilitzar els Repositories per accedir a les dades i processar-les segons les regles de negoci.

- **Transaccionalitat:** Es pot utilitzar l'anotació `@Transactional` per assegurar la integritat de les transaccions en mètodes específics.

#### Excepcions:

- **Maneig d'Excepcions:** És important manejar adequadament les excepcions que puguin ocórrer a l'aplicació. Es poden crear excepcions personalitzades estenent `RuntimeException` o `Exception` i manejar-les utilitzant `@ExceptionHandler` per respondre amb codis d'estat HTTP adequats i missatges d'error significatius.


#### Filters:
- **Maneig d'acces a l'aplicació** : És molt important els filtres ja que es uns de els pilars per fer la aplicació segura, s'encarrega de no donar acces a les pagines de l'aplicació sense estar Registrat i fet Login, en tot moment comproba que l'usuari te acces a la pagina.







----



### Resources:

En un projecte de Spring Boot, el directori `src/main/resources` conté recursos **estàtics** i configuracions de l'aplicació. Aquest directori és una convenció en projectes basats en Maven.

#### Què es tè resources?

1. **Fitxers de configuració:** S'utilitzen per configurar aspectes de l'aplicació, com propietats de connexió a la base de dades (`application.properties` o `application.yml`), configuracions de seguretat, etc.

Fitxer `application.properties` de la aplicació:

- Es troba la nostre configuració per conectar la aplicació a la Base de dades MySQL corrent en el docker.
	- **3 parametres:** 
		- **url**: de la base de dades.
		- **Nom d'usuari**
		- **Contrasenya**
	
```Java
spring.datasource.url=jdbc:mysql://127.0.0.1:3307/drawing  
spring.datasource.username = root  
spring.datasource.password = root
```


2. **Fitxers estàtics:** Recursos estàtics com arxius HTML, CSS, JavaScript, imatges, plantilles de vistes (Thymeleaf, FreeMarker), arxius d'internacionalització, etc., que són utilitzats per l'aplicació.
    
3. **Fitxers de dades inicials o SQL:** Fitxers que contenen dades inicials per a la base de dades (`data.sql`, `schema.sql`).


4. **Qualsevol recurs que l'aplicació necessiti carregar:** Això pot incloure arxius de propietats, XML, arxius de configuració específics per a certes llibreries o components, etc.
    

##### Ús de recursos a Spring Boot:

Spring Boot carrega automàticament els arxius ubicats a `src/main/resources` i els fa accessibles durant l'execució de l'aplicació. Per exemple, els fitxers de configuració es llegeixen automàticament per configurar aspectes de l'aplicació, els fitxers estàtics es serveixen a través de les rutes definides als controladors, i les dades inicials es poden carregar automàticament a la base de dades durant la inicialització.

En resum, el directori `src/main/resources` és crucial en un projecte de Spring Boot ja que conté recursos i configuracions necessaris per al funcionament de l'aplicació i el seu contingut es carrega automàticament en el classpath, permetent-ne l'ús en l'aplicació durant l'execució.

Recursos statics:
- A `src/main/resources/static` trobarem els fitxers de estils `css`,`images` i en aquest cas `favicon_package_v0-2` que conte els favicons per tots els navegadors Web utilitzats a dia de avui. 
	- Dins `css`tenim els següents estils :
		- `loginStyle`,`showCanvas`,`style`
	- Tambe trobam `static/js` que conte els fitxers  `alerts`,`fetchModify`,`lienzo`,`vista`,`localStorage` i `modify`
- A  `src/main/resources/template`
	- Conte els **html** següents `allCanvas`,`viewCanvas`,`trash`,`modify` , `login`, `register`, `canvasDraw` mes endevant vorem cada un de ells i la seva funció.


---

##  3. Descripció detallada de cada procediment 

>[!NOTE]
	>En aquesta segona versió de el Drawing se ha optat per explicar alguns procediments complets per una millor compresió de la logica de l'aplicació, aquest procediments van desde que el client interactua (Vista) fins a Repository que ho envia a la Base de dades
	



### Procediment de Registrar

Aquest és un sistema de registre web bàsic que utilitza HTML per a la interfície i Java amb Spring Boot per al backend. Aquí teniu el flux de registre:

1. **Formulari HTML**:
   - L'arxiu HTML proporciona un formulari senzill amb camps per a Nom, Correu Electrònic i Contrasenya.
   - El formulari té un botó de "Registra't" que envia les dades a través del mètode POST a la URL `/register`.

2. **Backend Java amb Spring Boot**:
   - Quan s'accedeix a `/register`, es mostra el formulari de registre (`showRegistrationForm()` al `RegisterController`).
   - Quan s'envia el formulari (`POST /register`), es crida a `processRegistration()` al `RegisterController`.
   - Les dades del formulari (nom, correu electrònic, contrasenya) es reben com a paràmetres `@RequestParam`.

3. **Validació i Processament**:
   - En `processRegistration()`, es valida la contrasenya per assegurar-se que tingui almenys 5 caràcters.
   - Si la contrasenya és vàlida, s'encripta i s'intenta registrar l'usuari utilitzant el servei `UserService`.

4. **UserService**:
   - `UserService` gestiona la lògica de negoci relacionada amb l'usuari.
   - A `registrarUsuari()`, la contrasenya es xifra i es crea un nou objecte `User` amb les dades proporcionades.
   - S'intenta desar l'usuari a la base de dades a través de `userRepo.save(user)`.
   - La contrasenya es xifra utilitzant l'algorisme MD5 abans de ser emmagatzemada.

5. **UserRepo (Repositori de l'Usuari)**:
   - `UserRepoImplSQL` implementa `UserRepo`.
   - S'utilitza `JdbcTemplate` per interactuar amb la base de dades SQL.
   - El mètode `save()` insereix les dades de l'usuari (name, email, password xifrada) a la base de dades.

6. **Respostes i Redireccions**:
   - Si el registre és exitós, l'usuari és redirigit a la pàgina d'inici de sessió (`/login`) amb un missatge d'èxit.
   - Si hi ha algun error durant el registre (per exemple, usuari existent), es mostra un missatge d'error a la pàgina de registre sense redireccionar.
   - 
----

### Procediment de Login


A continuació l'explicacio de el proces que fa l'aplicació Web cada vegada que un usuari fa el login, 

1. **Formulari HTML**:
   - La pàgina `login.html` conté un formulari senzill amb camps per a Correu Electrònic i Contrasenya.
   - Quan el formulari s'envia (`method="post"`), l'acció es dirigeix a `/login`.

2. **Controlador de Login** (`LoginController.java`):
   - L'anotació `@Controller` marca la classe com a controlador.
   - El mètode `showLoginForm()` respon a les sol·licituds `GET` a `/login` i mostra el formulari d'inici de sessió. Si hi ha una sessió existent, s'anul·la abans de mostrar el formulari.
   - El mètode `login()` respon a les sol·licituds `POST` a `/login` i maneja la lògica d'inici de sessió.
     - Obté les dades del formulari (`email` i `password`).
     - Verifica si l'usuari ha superat el nombre màxim d'intents d'inici de sessió (tres intents en aquest cas) i si hi ha un temps d'espera de 1 minut després dels tres intents fallits.
     - Crida al servei `UserService` per validar les credencials de l'usuari.

3. **Servei d'Usuari** (`UserService.java`):
   - El mètode `validLogin()` verifica si les credencials proporcionades (`email` i `password`) són vàlides.
   - En aquest cas, `validLogin()` utilitza el mètode `initSession()` al repositori d'usuaris per verificar les credencials.
   - També hi ha un mètode `getNameOfUser()` per obtenir el nom de l'usuari.

4. **Repositori d'Usuari** (`UserRepoImplSQL.java`):
   - Implementa la interfície `UserRepo` i utilitza `JdbcTemplate` per interactuar amb la base de dades SQL.
   - El mètode `initSession()` busca un usuari amb el correu electrònic i contrasenya proporcionats a la base de dades.
   - També hi ha un mètode `findByEmail()` per trobar un usuari pel seu correu electrònic i un mètode `save()` per desar un nou usuari a la base de dades.

5. **Entitat d'Usuari** (`User.java`):
   - Defineix l'estructura de l'entitat d'usuari amb camps com `id`, `name`, `email` i `password`.

6. **Gestió de Sessions**:
   - S'utilitza `HttpSession` per gestionar la sessió de l'usuari, emmagatzemant detalls com el nombre d'intents d'inici de sessió (`loginAttempts`), temps de l'últim intent de sessió fallit, etc.
   - Depenent del nombre d'intents d'inici de sessió i el temps transcorregut des de l'últim intent, es mostren missatges apropiats a l'usuari.


---

>[!NOTE]
	>Aquests dos procesos juntament amb tot el acces a dades fan us de *JdbcTemplete* que s'explicara a continuació i es proporcionara una taula de els metodes que es poden utilitzar i un petit exemple.


## **JdbcTemplete:**

`JdbcTemplate` a Spring Boot és una utilitat que simplifica i agilitza les operacions de la base de dades JDBC en Java proporcionant mètodes simples per executar consultes, actualitzacions i maneig d'excepcions.

1. **Configuració del DataSource**: Configures la informació de connexió a la base de dades.
2. **Injecció de Dependències**: Utilitzes `@Autowired` per obtenir un `JdbcTemplate`.
3. **Operacions de Base de Dades**: Executes consultes (`queryForObject`, `query`, `queryForList`) i actualitzacions (`update`) de manera senzilla.
4. **Gestió d'Excepcions**: Automatitza el tractament d'excepcions JDBC, convertint-les en excepcions més manejables de Spring.
5. **Transaccions**: Suporta transaccions amb anotacions de Spring (`@Transactional`), permetent operacions atòmiques en blocs de codi.
6. **Alliberament de Recursos**: S'encarrega de tancar connexions i alliberar recursos automàticament.
7. **Facilitat d'Ús**: Simplifica el codi eliminant tasques repetitives en operacions amb bases de dades, com la gestió de connexions i excepcions.

#### Taula de mètodes:

| Mètode                                       | Descripció                                                                                                              | Exemple                                                                                                           |
|----------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| `query`                                      | Executa una consulta SQL i mapeja els resultats a una llista d'objectes usant un `RowMapper`.                           | ```java List<User> usuaris = jdbcTemplate.query("SELECT * FROM users", new UserRowMapper()); ```                 |
| `queryForObject`                             | Executa una consulta SQL i retorna un sol objecte, mapejat mitjançant un `RowMapper`.                                      | ```java User usuari = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{userId}, new UserRowMapper()); ``` |
| `update`                                     | Executa una operació d'actualització (INSERT, UPDATE, DELETE) a la base de dades.                                      | ```java int filesAfectades = jdbcTemplate.update("UPDATE users SET name = ? WHERE id = ?", "John Doe", userId); ``` |
| `batchUpdate`                                | Executa una sèrie d'operacions d'actualització en lots.                                                                 | ```java int[] filesAfectades = jdbcTemplate.batchUpdate("INSERT INTO users (name) VALUES (?)", batchArgs); ```    |
| `execute`                                    | Executa una acció amb la base de dades sense paràmetres d'entrada.                                                         | ```java jdbcTemplate.execute("CREATE TABLE example (id INT, name VARCHAR(255))"); ```                             |
| `queryForList`                               | Executa una consulta SQL i retorna els resultats com una llista de mapes (generalment per a dades simples).            | ```java List<Map<String, Object>> usuaris = jdbcTemplate.queryForList("SELECT * FROM users"); ```                   |
| `queryForMap`                                | Executa una consulta SQL i retorna un sol resultat com un mapa de columnes a valors (generalment per a dades simples). | ```java Map<String, Object> usuari = jdbcTemplate.queryForMap("SELECT * FROM users WHERE id = ?", userId); ```       |
| `queryForObject` (amb RowMapper i paràmetres) | Similar a `queryForObject`, però amb paràmetres addicionals per passar valors i un `RowMapper` per mapejar objectes.      | ```java User usuari = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{userId}, new UserRowMapper()); ``` |


---

## CanvasController


1. **Interfície de la pàgina HTML:**
   - Es defineix una estructura HTML que conté un canvas per dibuixar i un conjunt de controls (botons, selectores, etc.) per seleccionar el tipus de figura, el color, la mida, etc.
   - S'han afegit esdeveniments a diferents elements de la interfície (clics, canvis, etc.) que activen diverses funcions de JavaScript.

2. **JavaScript (`lienzo.js`):**
   - Es defineix una classe `Drawing` que conté mètodes per afegir figures i traços al canvas, així com per obtenir les dades en format JSON.
   - Es defineixen funcions per dibuixar diferents figures (cercles, quadrats, triangles, estrelles) al canvas, depenent de la selecció de l'usuari.
   - Es gestionen esdeveniments del ratolí (clic, moviment, alliberament) per dibuixar al canvas.
   - Es vincula l'esdeveniment de clic del botó "Desar" (`#guardarDibuix`) per recopilar les dades de les figures i traços en format JSON i enviar-les al controlador mitjançant un formulari ocult.

3. **Controlador Java (`CanvasController.java`):**
   - El controlador defineix dos mètodes (`getCanvasPage` i `saveCanvas`) que gestionen les sol·licituds GET i POST per la pàgina del canvas.
   - El mètode `getCanvasPage` retorna la vista Thymeleaf "canvasDraw" amb el nom d'usuari obtingut de la sessió.
   - El mètode `saveCanvas` s'activa quan s'envia el formulari des de la pàgina HTML. Aquest mètode recull les dades de les figures i traços del canvas a través dels paràmetres de la sol·licitud (`strokesData`, `figuresData`, etc.). Després, utilitza el servei `canvasServices` per desar aquestes dades a la base de dades.


### CanvasServices:

Ès fa càrrec de gestionar les operacions relacionades amb els Canvas i les seves versions. Te els següents mètodes:
### Mètodes:

1. **`newCanvas`**: Crea un nou Canvas amb les dades proporcionades i el desa a la base de dades. Rep com a paràmetres el JSON de traços i figures, el correu electrònic de l'usuari, el nom del Canvas i un indicador de si és públic.

2. **`showMyCanvas`**: Retorna la llista de Canvas pertanyents a un usuari específic identificat pel seu correu electrònic.

3. **`generarNom`**: Genera un nom per a un Canvas si l'usuari no n'ha proporcionat un.

4. **`deleteCanvasById`**: Elimina un Canvas segons el seu ID i el correu electrònic del propietari.

5. **`sendToTrash`**: Mou un Canvas a la paperera segons el seu ID i el correu electrònic del propietari.

6. **`sendOutToTrash`**: Restaura un Canvas de la paperera a la seva ubicació original segons el seu ID i el correu electrònic del propietari.

7. **`getCanvasToModify`**: Obté els detalls d'un Canvas per a la seva modificació. Verifica si el Canvas pertany a l'usuari de la sessió actual.

8. **`showAllCanvas`**: Retorna una llista de tots els Canvas amb les seves versions associades per a un usuari específic.

9. **`getCanvas` i `getVersion`**: Retorna un Canvas o la seva versió, respectivament, segons el seu ID i el correu electrònic de l'usuari.

10. **`showMyTrash`**: Retorna la llista de Canvas que estan a la paperera d'un usuari específic.

### Funcionalitats

- **Crear Canvas**: Amb `newCanvas`, es crea un Canvas amb les dades proporcionades i es guarda a la base de dades.Es guarda Canvas i tambe Versio
  
- **Gestió de Canvas**: Es poden obtenir, modificar, eliminar, enviar a la paperera o restaurar Canvas mitjançant mètodes com `deleteCanvasById`, `getCanvasToModify`, `sendToTrash`, `sendOutToTrash` i `showMyTrash`.

- **Visualització de Canvas**: `showMyCanvas`, `showAllCanvas`, `getCanvas` i `getVersion` permeten veure els Canvas disponibles per a un usuari en particular.

- **Noms de Canvas**: `generarNom` genera un nom per a un Canvas si no se n'ha proporcionat cap. 

Aquest servei proporciona operacions completes per gestionar i visualitzar Canvas i les seves versions associades, permetent als usuaris realitzar diverses accions com crear, modificar, eliminar, enviar a la paperera o restaurar Canvas.


---
Clar, aquest codi representa un servei (`VersionService`) en una aplicació Spring Boot que gestiona les versions d'un canvas. Aquí tens un anàlisi detallat del procediment implementat en aquest servei:

#### Mètode `newVersionCanvas`

1. **Rebre Dades**:
   - Rep un objecte `CanvasVersionDTO` que conté informació sobre la nova versió del canvas a crear o actualitzar.

2. **Creació de la Versió**:
   - Crea una instància de la classe `Version` i assigna els valors del `CanvasVersionDTO` rebut a aquesta instància.
   - La informació s'extreu del DTO i s'assigna als camps corresponents a l'entitat `Version`.

3. **Verificació de Permisos**:
   - Utilitza el repositori `versionRepo` per verificar si l'usuari té permisos d'escriptura per a la versió del canvas.

4. **Comparació de Canvis**:
   - Compara els canvis entre la nova versió i la versió anterior (si existeix) del canvas.
   - Avalua si les dades de la versió actual són diferents a les dades anteriors.
   - També verifica si el nom del canvas o la visibilitat han canviat.

5. **Emmagatzematge de la Nova Versió o Actualització**:
   - Si l'usuari té permisos i es detecten canvis, s'emmagatzema la nova versió utilitzant el repositori `versionRepo`.
   - En cas que els canvis només involucrin el nom o la visibilitat del canvas, actualitza aquests atributs en lloc de crear una nova versió.


#### **compareCanvasAtributtesChange**:

   - Compara si hi ha hagut canvis en els atributs del canvas (nom o visibilitat) entre la versió anterior i la versió actual.Si no hi ha cambis retorna false i no es creara una nova versio de el canvas, aquesta funció se ha creat sobretot per donar soport a `compareVersionChange`ja que aixi se separa la logica de els atributs que se han de canviar a CanvasRepo de els de VersionRepo. Es crida cada pic que entren dades a `newVersionCanvas`.
   

### **getAllVersion**:
   - Obté totes les versions d'un canvas amb un identificador específic utilitzant el repositori `versionRepo`.

#### **compareVersionChange**:
   - Compara si hi ha canvis entre la nova versió i la versió anterior del canvas, específicament en les dades de traços i figures.Es crida cada pic que entren dades a `newVersionCanvas`.


- El servei interactua amb repositoris (`CanvasRepo` i `VersionRepo`) per gestionar les operacions d'emmagatzematge i recuperació de dades.
- Utilitza el DTO `CanvasVersionDTO` per transportar les dades necessàries per crear o actualitzar una versió del canvas , majoritariament te el ús de guardar les dades de Canvas i Versio juntes menys els atributs en comu esta clar.

El servei de `VersionService`  és responsable de gestionar la creació i actualització de versions de canvas, verificant els permisos, comparant canvis i actuant en conseqüència per emmagatzemar o actualitzar les dades segons correspongui.

---

#### PermissionService 
Ès'encarrega de concedir permisos sobre un canvas a un usuari específic. Aquí tens els detalls del procediment:

### Mètode `givePermission`

1. **Creació d'un Objecte `Permission`**:
   - S'instancia un objecte `Permission` per emmagatzemar la informació del permís que es concedirà o actualitzar.
   - Es defineix l'email de l'usuari al qual se li concedirà el permís i l'ID del canvas al qual s'aplicarà el permís.

2. **Validació del Tipus de Permís**:
   - El servei espera un tipus de permís especificat a la propietat `permissionType` del DTO `CanvasPermissionDTO`.
   - Converteix el tipus de permís a majúscules i el guarda a `typePer`.
   - Verifica que el tipus de permís sigui vàlid (per exemple, "R" per a lectura o "W" per a escriptura).
   

3. **Assignació del Permís**:
   - Si el tipus de permís és vàlid, s'assigna a l'objecte `Permission` creat anteriorment.
   - Si el tipus de permís no és vàlid (no és "R" ni "W"), el servei considera que no és una entrada vàlida i retorna `false`.


>[!NOTE]
	> Si un usuari te permissionType = "W" significa que te permis de Lectura i escritura, i si te permissionType de "R" nomes tendra permis de lectura.

	

1. **Obtenció del Propietari del Canvas**:
   - Recupera l'email del propietari del canvas des del DTO `CanvasPermissionDTO`.

2. **Concessió del Permís**:
   - Utilitza el repositori `permissionRepo` per concedir el permís.
   - La lògica de concessió de permisos es realitza al mètode `getPermission` del repositori `PermissionRepo`.

3. **Retorn del Resultat**:
   - Retorna `true` si el permís s'atorga correctament.
   - Retorna `false` si el tipus de permís especificat no és vàlid o si hi ha algun error durant el procés de concessió de permisos.

- Aquest servei depèn dels repositoris `PermissionRepo` i `UserRepo` per accedir i manipular les dades relacionades amb els permisos i els usuaris a la base de dades.
- Utilitza el DTO `CanvasPermissionDTO` per transportar les dades necessàries per concedir permisos sobre un canvas a un usuari específic.

El servei de `PermissionService`servei actua com a intermediari per concedir permisos sobre un canvas a un usuari determinat. Valida el tipus de permís, recupera la informació necessària i després utilitza el repositori corresponent per dur a terme l'acció de concedir el permís a la base de dades.





---

## DTOs

### **CanvasPermissionDTO** :
És un objecte de transferència de dades (DTO, per les seves sigles en anglès), utilitzat per transportar informació específica sobre els permisos d'un Canvas entre diferents capes d'una aplicació. Veurem els detalls:

### Estructura de la Classe

- **Anotació `@Component`**: Indica que aquesta classe ha de ser tractada com un component pel contenidor de Spring.

- **Camps**:
    - `idCanvas`: Identificador del Canvas.
    - `permissionType`: Tipus de permís concedit.
    - `user_email`: Correu electrònic de l'usuari al qual se li concedeix el permís.
    - `owner_email`: Correu electrònic del propietari del Canvas.

- **Constructors**:
    - `CanvasPermissionDTO()`: Constructor per defecte.
    - `CanvasPermissionDTO(int idCanvas, String permissionType, String user_email, String owner_email)`: Constructor que rep els paràmetres per inicialitzar els camps.

- **Mètodes Accessors**:
    - Mètodes `get` i `set` per accedir i modificar els camps de la classe.

- **Mètode `toString()`**:
    - Genera una representació de cadena de text d'un objecte `CanvasPermissionDTO`, mostrant els valors dels seus camps.

### Ús de la Classe

Aquesta classe s'utilitza per transportar dades sobre els permisos d'un Canvas entre diferents parts de l'aplicació. Per exemple:

- Pot ser emprada per obtenir i mostrar informació sobre els permisos d'un Canvas específic per a un usuari determinat.
- Facilita la transferència de dades sobre els permisos d'un Canvas entre la capa de presentació i la lògica de negoci, o entre la lògica de negoci i la capa d'accés a dades.
- S'utilitza a les següents clases :
	- `ModifyController`,`TrashController`, `ViewController`, `CanvasServices`,`VersionServices`.
	
### Importància del DTO

Els DTO són útils per separar la lògica de negoci de l'estructura de les dades que s'utilitzen per transportar informació. Ajuden a mantenir una separació clara entre les diferents capes d'una aplicació i a evitar l'exposició directa dels detalls interns d'implementació en altres components. En aquest cas, el `CanvasPermissionDTO` s'encarrega d'encapsular els detalls sobre els permisos d'un Canvas, permetent un intercanvi fàcil i segur d'aquesta informació entre diferents parts del sistema.

---
Aquesta classe `CanvasVersionDTO` és un objecte de transferència de dades (DTO) que representa una versió del canvas a la teva aplicació. Veurem els detalls d'aquesta classe:

### Estructura de la Classe

- **Anotació `@Component`**: Indica que aquesta classe ha de ser tractada com un component pel contenidor de Spring.

- **Camps**:
    - `idObjectes`: Identificador dels objectes del canvas.
    - `nameCanvas`: Nom del canvas.
    - `user_email`: Correu electrònic de l'usuari associat al canvas.
    - `dataCreacio`: Data i hora de creació del canvas.
    - `numberObject`: Nombre d'objectes al canvas.
    - `figures`: Representació en format JSON de les figures del canvas.
    - `strokes`: Representació en format JSON dels traços del canvas.
    - `trash`: Indicador booleà que especifica si el canvas està a la paperera.
    - `dateLastModified`: Data i hora de l'última modificació del canvas.
    - `version`: Nombre de versió del canvas.
    - `isPublic`: Indica si el canvas és públic o no.

- **Constructors**:
    - `CanvasVersionDTO()`: Constructor per defecte.
    - `CanvasVersionDTO(int idObjectes, String nameCanvas, String user_email, Instant dataCreacio, int numberObject, String figures, String strokes, boolean trash, Instant dateLastModified, int version, boolean isPublic)`: Constructor que rep paràmetres per inicialitzar els camps.

- **Mètodes Accessors**:
    - Mètodes `get` i `set` per accedir i modificar els camps de la classe.

- **Mètode `toString()`**:
    - Genera una representació de cadena de text d'un objecte `CanvasVersionDTO`, mostrant els valors dels seus camps.

### Ús de la Classe

Aquesta classe s'utilitza per transportar dades sobre una versió específica d'un canvas. Per exemple:

- Pot ser utilitzada per obtenir informació detallada sobre una versió particular del canvas, incloent dades com la data de creació, la quantitat d'objectes, la informació de les figures i els traços, etc.
- Facilita la transferència d'informació sobre les versions dels canvases entre les diferents capes de l'aplicació, permetent una separació clara entre la capa de presentació, la lògica de negoci i la capa d'accés a dades.

 En aquest cas, el `CanvasVersionDTO` encapsula els detalls d'una versió del canvas, facilitant el seu intercanvi entre diferents components de l'aplicació i mantenint una clara separació entre les capes del sistema.

----
### ModifyCanvasVersionDTO:
És un altre objecte de transferència de dades (DTO) utilitzat per representar les dades necessàries per modificar una versió existent d'un canvas a l'aplicació.

#### Estructura de la Classe

- **Anotació `@Component`**: Indica que aquesta classe ha de ser tractada com un component pel contenidor de Spring.

- **Camps**:
    - `strokesData`: Dades dels traços del canvas en un format específic.
    - `figuresData`: Dades de les figures del canvas en un format específic.
    - `nameCanvas`: Nom del canvas que es vol modificar.
    - `isPublic`: Indica si el canvas modificat serà públic o no.

- **Constructors**:
    - `ModifyCanvasVersionDTO()`: Constructor per defecte.
    - `ModifyCanvasVersionDTO(String strokesData, String figuresData, String nameCanvas, boolean isPublic)`: Constructor que rep paràmetres per inicialitzar els camps.

- **Mètodes Accessors**:
    - Mètodes `get` i `set` per accedir i modificar els camps de la classe.

- **Anotació `@JsonProperty("nomDibuix")`**:
    - Aquesta anotació indica que el camp `nameCanvas` en l'objecte JSON que s'envia o es rep es mapejarà amb la propietat "nomDibuix". És útil per personalitzar el nom del camp en el procés de serialització/deserialització JSON.

- **Mètode `toString()`**:
    - Genera una representació de cadena de text d'un objecte `ModifyCanvasVersionDTO`, mostrant els valors dels seus camps.

#### Ús de la Classe

Aquest DTO s'utilitza per transportar dades específiques necessàries per modificar una versió existent d'un canvas. Per exemple:

- En rebre una sol·licitud per modificar un canvas, es poden utilitzar objectes d'aquesta classe per encapsular les dades enviades des del client (com les dades de traços i figures, el nom del canvas i la informació de visibilitat pública).
- Facilita la transferència de dades necessàries per la modificació de la versió del canvas entre diferents capes de l'aplicació.

---

## Permission

- Ha continuació explicarem tota la secuencia que es segueix per donar permisos a un altre usuari.Anirem desde la vista fins el Repo.


`viewCanvas`encarrega de concedir permisos de lectura (`R`) o escriptura (`W`) a altres usuaris sobre un canvas. I al mateix temps permet veure el Canvas sempre que tenguem permis de lectura i inclus ens permet veure diferentes Versions de un mateix Canvas.
- Una part important tambe es la de poder realitzar una copia de un Canvas i pasa a ser una Versio independent de el Canvas original.

A continuacio explicam tot el proces en detall:

1. **HTML**: La pàgina web mostra un canvas per visualitzar les figures, un desplegable amb opcions de dates i una taula amb llistat d'usuaris, on hi ha botons per assignar-los permisos de lectura o escriptura.

2. **JavaScript (`fetch.js`)**:
   - `sendPermissionRequest(permissionType, button)`: Aquesta funció s'activa en clicar els botons de lectura o escriptura a la taula d'usuaris. Obté el email de l'usuari des de la taula i fa una sol·licitud `fetch` al servidor, enviant el tipus de permís (`permissionType`) i email de l'usuari seleccionat.

3. **JavaScript (`vista.js`)**:
   - Aquí es defineix la lògica per dibuixar figures i tracos al canvas a partir de dades emmagatzemades anteriorment.

4. **Controlador Java (`ViewController`)**:
   - El mètode `postViewCanvas` gestiona les sol·licituds `POST` del client i s'activa quan es fa clic als botons de permisos (`Lectura` o `Escriptura`). Agafa les dades del formulari, incloent el tipus de permís i el correu electrònic de l'usuari seleccionat, i els envia al servei `PermissionService`.

5. **Servei de permisos (`PermissionService`)**:
   - `givePermission`: Rep les dades de permisos (`CanvasPermissionDTO`) i verifica si el tipus de permís és vàlid (lectura o escriptura). Si és vàlid, utilitza un repositori per interactuar amb la base de dades i actualitzar els permisos d'acord amb la lògica de negoci definida.

6. **Repositori de permisos (`PermisionRepoImpl`)**
	Interactua amb la base de dades per gestionar els permisos d'accés als lienzos (`Canvas`) a l'aplicació.
	
	*A continuació una explicació detallada:*
	
	- **`@Repository`**: Aquesta anotació marca aquesta classe com un component de repositori, indicant que està destinada a manejar operacions d'accés a la base de dades.
	
	- **`JdbcTemplate`**: És una classe de Spring que simplifica la interacció amb la base de dades utilitzant JDBC (Java Database Connectivity). S'utilitza per executar consultes SQL i actualitzar la base de dades.
	
	- **`getPermission`**: Aquest mètode verifica i gestiona els permisos d'accés al canvas per a un usuari específic.
	  - Rep un objecte `Permission` i el correu electrònic del propietari (`owner_email`) com a paràmetres.
	  - Obté l'ID del canvas, el correu electrònic de l'usuari i el tipus de permís de l'objecte `Permission`.
	  - Realitza una verificació inicial per assegurar-se que el propietari tingui accés al canvas que es modifica.
	  - Després, verifica si ja existeix un registre a la taula `Permission` per a l'usuari i el canvas donat.
	    - Si existeix, actualitza el tipus de permís en el registre existent.
	    - Si no existeix, crea un nou registre amb el tipus de permís especificat.
	
	- **Consultes SQL**:
	  - S'utilitzen consultes SQL senzilles per fer aquestes verificacions i actualitzacions a la base de dades.
	  - `SELECT COUNT(*) FROM Canvas...`: Verifica si el propietari té accés al canvas.
	  - `SELECT COUNT(*) FROM Permission...`: Verifica si ja existeix un registre de permís per a l'usuari i el canvas.
	  - `UPDATE Permission SET...`: Actualitza el tipus de permís al registre existent.
	  - `INSERT INTO Permission...`: Crea un nou registre de permís si no existeix.
	
	- **Retorn**:
	  - El mètode retorna un booleà (`true` o `false`) segons l'èxit de l'operació d'actualització o inserció a la base de dades.

	Aquest repositori actua com una capa d'abstracció per interactuar amb la base de dades, proporcionant mètodes per verificar i administrar els permisos d'accés als canvas a l'aplicació, actualitzant o inserint registres a la taula `Permission` segons sigui necessari.

	Resumint es fa càrrec de gestionar les sol·licituds del client per assignar permisos de lectura o escriptura a altres usuaris sobre un canvas específic. Validen i actualitzen els permisos a la base de dades per garantir un accés controlat als recursos del canvas.


---
Entesos, aquest codi HTML i JavaScript està dissenyat per modificar un canvas de dibuix i desar els canvis automàticament mitjançant `fetch`. Deixa'm explicar-ho pas a pas:

### HTML:
1. **Canvas:** El codi HTML defineix una àrea de dibuix `<canvas>` amb un ID de "lienzo" on es poden dibuixar figures.
2. **Controls i Elements:** Hi ha elements per controlar la selecció de figures, opcions de dibuix, nom del canvas, color, mida, etc. Aquests elements interactuen amb el canvas.
3. **Importacions de Scripts:** S'importen diversos fitxers JavaScript al final del document.

### JavaScript (modify.js):
1. **Variables de Referència:** S'obtenen referències a elements HTML importants com el canvas, les dades JSON del canvas actual, controls de dibuix, etc.
2. **Carregar Figures i Ratllats:** En carregar la pàgina, es carreguen figures i ratllats prèviament guardats al canvas. La funció `carregarFiguresIRatllats()` s'encarrega d'això.
3. **Gestió del Dibuix:** Es gestionen esdeveniments del canvas per dibuixar figures, capturar ratllats dibuixats a mà alçada, i desar aquests ratllats i figures en arrays.
4. **Desament de Dades:** La funció `desarDades()` s'utilitza per guardar les dades del canvas al servidor a través d'una sol·licitud `fetch` a la ruta `/modify` usant el mètode `POST`. Aquesta funció s'activa quan el ratolí surt de l'àrea del canvas o abans que l'usuari abandoni la pàgina.

### fetchModify.js:
1. **Desar Figures:** Defineix una funció `desarFigures()` que s'utilitza per enviar les figures i altres dades al servidor a través d'una sol·licitud `fetch` a l'endpoint `/modify`.
2. **Esdeveniment de Desament:** Associa la funció `desarFigures()` a un botó de desar al canvas.
3. **Gestió d'Esdeveniments del Ratolí:** Escolta esdeveniments d'entrada/sortida del ratolí al canvas per determinar quan guardar les dades automàticament després d'un cert temps d'inactivitat.
4. **Gestió de Dades Locals:** També hi ha un fitxer `localStorage.js` que s'ocupa de desar i carregar dades localment al navegador de l'usuari per recordar la configuració i estat del canvas.

### Controlador de Spring Boot (ModifyController):
1. **GET `/modify`:** Obté les dades necessàries per a la modificació del canvas, com figures i ratllats, i les envia a la vista HTML.
2. **POST `/modify`:** Rep les dades actualitzades del canvas des del client i realitza el processament i desament d'aquestes dades al backend.

### Funcionament General:
- En carregar la pàgina, es recuperen les dades del canvas des del backend i es carreguen al canvas per a la seva visualització i modificació.
- L'usuari pot dibuixar figures i ratllats al canvas.
- Els canvis es desen automàticament després d'un cert temps d'inactivitat o quan l'usuari surt de l'àrea del canvas.
- El desament automàtic es realitza mitjançant una sol·licitud `fetch` al backend.

Aquest codi permet la interacció en temps real amb un canvas de dibuix, la modificació i el desament dels canvis de manera automàtica i manual a través de crides al servidor.

### VersionService:
- Aquest servei es responsabilitza de gestionar les versions dels dibuixos al sistema.
- Té mètodes per crear noves versions d'un canvas, obtenir totes les versions d'un canvas específic i comparar canvis entre versions.

### newVersionCanvas():
- Rep un `CanvasVersionDTO` que conté informació sobre la versió actual del dibuix.
- Crea una nova versió del canvas basada en els canvis proporcionats, verifica permisos i detecta canvis en les dades del canvas.
- S'assegura que els canvis es desin adequadament a la base de dades.

### compareCanvasAtributtesChange():
- Compara els atributs del canvas (com el nom i la visibilitat) per determinar si han canviat.
- Utilitza dades de la base de dades per verificar els canvis i decideix si s'han d'aplicar aquests canvis al canvas.

### compareVersionChange():
- Compara dues versions del canvas per determinar si hi ha hagut canvis en els traços o figures.
- Analitza els traços i figures actuals amb les versions anteriors per detectar si s'han realitzat canvis.

### VersionRepoImpl:
- Aquest repositori gestiona les consultes a la base de dades per interactuar amb les taules de versions i permisos d'accés.

### getVersionsByIdDraw():
- Obté totes les versions d'un canvas específic basat en el seu identificador.
- Realitza consultes a la base de dades per obtenir informació sobre les versions emmagatzemades.

### verifyUserCanWrite() i verifyUserCanRead():
- Verifiquen si un usuari té permisos d'escriptura o lectura sobre un dibuix en particular basant-se en la informació de permisos emmagatzemada a la base de dades.

### getLastVersionByCanvasId():
- Obté l'última versió d'un canvas basada en el seu identificador.
- Realitza una consulta per obtenir l'última versió registrada a la base de dades.

### changeNameAndVisibility() i newVersionOfCanvas():
- Actualitzen el nom i la visibilitat del canvas a la base de dades, respectivament, basant-se en els canvis detectats.

En resum, aquest conjunt de classes i mètodes es responsabilitza de gestionar les versions dels dibuixos, verificar permisos d'accés i actualitzar les dades relacionades amb els canvas en una base de dades, assegurant la integritat de la informació i aplicant els canvis necessaris segons les actualitzacions realitzades al client.


---
Aquest codi HTML i JavaScript implementa una interfície web per a una paperera de llibrets. Veurem com funciona:

### Estructura HTML
- La estructura general de l'HTML defineix una pàgina web amb una capçalera, una taula per mostrar els llibrets a la paperera i alguns scripts al final.
- S'utilitza Thymeleaf (`th:`) per incloure valors dinàmics a la pàgina, com les dades dels llibrets.

### Funcions JavaScript
- `confirmDelete()`: Una funció JavaScript que mostra un missatge de confirmació abans d'eliminar definitivament un llibret. S'utilitza en l'esdeveniment `onsubmit` del formulari d'eliminació.
- `sendPermissionRequest()`: Una funció que envia una sol·licitud al servidor quan es sol·licita un permís relacionat amb un llibret. Utilitza `fetch` per fer una sol·licitud POST a una URL específica (`/viewCanvas`).

### Controlador Java (TrashController)
- Fa mapeig de les URL `/trash`, `/trash/delete` i `/trash/exitTrash` a mètodes específics.
- `showTrash()`: Recupera els llibrets a la paperera associats a un usuari específic i els mostra a la pàgina.
- `deleteCanvas()`: Elimina un llibret de la paperera basant-se en el seu identificador.
- `recoverCanvas()`: Restaura un llibret des de la paperera a la llista principal de llibrets.

### Serveis (CanvasServices)
- Mètodes com `deleteCanvasById()`, `sendToTrash()` i `sendOutToTrash()` a `CanvasServices` manipulen els llibrets a la base de dades, borrant-los, mouent-los a la paperera o treient-los de la paperera segons sigui necessari.

### Repositori (CanvasRepoImpl)

El repositori (`CanvasRepoImpl`) és una part essencial de l'arquitectura d'aquest sistema. S'encarrega de gestionar la comunicació amb la base de dades per realitzar operacions relacionades amb els llibrets. Aquí tens un anàlisi més detallat de com està estructurat:

- Els mètodes `removeCanvas()`, `goToTrash()` i `sendOutOfTrash()` implementen les operacions de base de dades per manipular els llibrets segons el sol·licitat pels serveis.

- A continuació la explicació en detall de aquests mètodes:

1. **deleteCanvasById(int idDelete, String email)**
    - Elimina un llibret de la base de dades segons el seu identificador (`idDelete`) i el correu electrònic associat (`email`) del propietari del llibret.
    - Utilitza SQL per dur a terme l'eliminació a la base de dades.

2. **sendToTrash(int idCanvasTrash, String email)**
    - Mou un llibret a la paperera (marcant l'atribut `trash` com a `true`) basant-se en el seu identificador (`idCanvasTrash`) i el correu electrònic del propietari (`email`).
    - Aquesta operació canvia l'estat del llibret a "a la paperera".

3. **sendOutToTrash(int idCanvasTrash, String email)**
    - Restaura un llibret de la paperera a la llista principal de llibrets.
    - Similar a `sendToTrash()`, canvia l'estat del llibret a "fora de la paperera" (marcant l'atribut `trash` com a `false`).

En resum, aquesta implementació HTML/JavaScript i la seva lògica Java gestionen una paperera(`trash`) de llibrets, permetent als usuaris veure, eliminar i restaurar llibrets des de la paperera a través d'una interfície web. Les dades es manipulen al backend a través de serveis i repositoris, connectant amb la base de dades per realitzar les operacions necessàries.


---


>[!IMPORTANT]
	>Per probar el programa tots els usuaris tenen la mateixa *password* que es ***hola1***
	
## La Base de 	Dades te les següent estructura i taules:


```Mysql
CREATE TABLE `user` (  
`id` int(11) NOT NULL AUTO_INCREMENT,  
`name` varchar(100) NOT NULL,  
`email` varchar(255) NOT NULL,  
`password` varchar(255) NOT NULL,  
PRIMARY KEY (`id`),  UNIQUE KEY `email` (`email`),  
UNIQUE KEY `email_2` (`email`) )

CREATE TABLE `Canvas` (  
`idObjectes` int(11) NOT NULL AUTO_INCREMENT,  
`nameCanvas` varchar(255) DEFAULT NULL,  
`dataCreacio` datetime DEFAULT CURRENT_TIMESTAMP,  
`user_email` varchar(255) NOT NULL,  
`trash` tinyint(1) DEFAULT NULL,  
`public` tinyint(1) DEFAULT NULL,  
PRIMARY KEY (`idObjectes`),  
KEY `user_email` (`user_email`),  
CONSTRAINT `Canvas_ibfk_2` FOREIGN KEY (`user_email`) 
REFERENCES `user` (`email`) )

CREATE TABLE `Version` (  
`idVersion` int(11) NOT NULL AUTO_INCREMENT,  
`idDraw` int(11) NOT NULL,  
`figuresJSON` text NOT NULL,  
`strokesJSON` text NOT NULL,  
`dateLastModified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  
`user_email` varchar(255) NOT NULL,  
`numberObject` int(11) NOT NULL,  
PRIMARY KEY (`idVersion`),  KEY `Version_ibfk_2` (`user_email`),  
KEY `Version_ibfk_1` (`idDraw`),  
CONSTRAINT `Version_ibfk_1` FOREIGN KEY (`idDraw`) 
REFERENCES `Canvas` (`idObjectes`) ON DELETE CASCADE ON UPDATE CASCADE,  CONSTRAINT `Version_ibfk_2` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`) )



CREATE TABLE `Permission` (  
`idCanvas` int(11) NOT NULL,  
`permissionType` varchar(1) NOT NULL,  
`user_email` varchar(255) NOT NULL,  
KEY `Permission_ibfk_1` (`idCanvas`),  
KEY `Permission_ibfk_2` (`user_email`),  
CONSTRAINT `Permission_ibfk_1` FOREIGN KEY (`idCanvas`) 
REFERENCES `Canvas` (`idObjectes`) ON DELETE CASCADE,  CONSTRAINT `Permission_ibfk_2` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`) ON DELETE CASCADE )
```



## 4. Tecnologies utilitzades
- Maven
- Github
- Canvas
- IntelliJ IDEA
- Visual Studio Code
- Tomcat
- Docker
- Spring Boot
- MySQL
- Markdown(eina Obsidian)
- JdbcTemplete
- Fetch
- Local Storage
- ECMAScript Modules
- Thymeleaf