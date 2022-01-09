1. QueryDSL Predicate
   - https://habr.com/ru/post/344450/
2. Criteria API
3. CASE IF
- https://metanit.com/sql/mysql/6.4.php

Вы пишете: "В терминах JPQL и SQL выразить такой запрос невозможно" для случая, когда нужно комбинировать несколько разных, заранее неизвестных фильтров. На днях наткнулся на такое решение:
WHERE (? OR name LIKE ?) AND (? OR passport.serial = ?) AND (? OR passport.number = ?)
Если нужно фильтровать по имени, то первый вопрос в группе (? OR name LIKE ?) заменяем на 0, а второй на нужный кусочек имени. Тоже самое для остальных групп.
Так что выразить такой запрос в терминах SQL возможно.
P. S. За ресурс большое спасибо.
4. Specifications
  - https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/
  - https://easyjava.ru/data/jpa/jpa-criteria/
  - https://easyjava.ru/spring/spring-data-project/ispolzovanie-jpa-criteria-v-spring-data-jpa/
  - https://www.baeldung.com/rest-api-search-language-spring-data-specifications


Good article about specifications
https://medium.com/geekculture/spring-data-jpa-a-generic-specification-query-language-a599aea84856


JPA Specifications:
Pros:

Cons:
Q: This does not provide examples of selecting a few columns as part of the query. I have more than 80 columns in my Entity class but want to retrieve only a few of them. How do I do it using Specification and QueryDSL
A: You cant do it with Specifications. Best thing is to use custom query with entity manager

You should use 

Useful links:
https://easyjava.ru/spring/spring-data-project/ispolzovanie-jpa-criteria-v-spring-data-jpa/
https://attacomsian.com/blog/spring-data-jpa-specifications

Alternatives:
-QueryDSL