# Task-Autonomous-systems
Пример запуска(терминал InteliJ):

Ввод: ya.ru
Вывод:
№|                                    IP - Adress|         Autonomous-system| COUNTRY
1|             2a01:540:4:38c9:b8fb:668c:854:4d17|   AS12389 PJSC Rostelecom| RU
2|         fc00:540:8000:ffff:ffff:ffff:ffff:ffac|                      null| NULL
3|                            2a01:620::57e2:8682|   AS12389 PJSC Rostelecom| RU
4|                             2a01:620:1:20ae::2|   AS12389 PJSC Rostelecom| RU
5|                          2a02:6b8:0:1a00::fffe|        AS13238 YANDEX LLC| RU
6|                                2a02:6b8::2:242|        AS13238 YANDEX LLC| RU

Ввод: google.com
Вывод:
№|                                    IP - Adress|         Autonomous-system| COUNTRY
1|             2a01:540:4:38c9:b8fb:668c:854:4d17|   AS12389 PJSC Rostelecom| RU
2|         fc00:540:8000:ffff:ffff:ffff:ffff:ffac|                      null| NULL
3|                            2a01:620::57e2:8682|   AS12389 PJSC Rostelecom| RU
4|                            2a01:620::5fa7:5828|   AS12389 PJSC Rostelecom| RU
5|                        2a00:1450:8000:293::1:1|        AS15169 Google LLC| US
6|                            2001:4860:0:1::260e|        AS15169 Google LLC| US
7|                            2001:4860:0:1170::2|        AS15169 Google LLC| US
8|                         2001:4860::c:4002:9189|        AS15169 Google LLC| US
9|                        2001:4860::cc:4001:16c8|        AS15169 Google LLC| US


Process finished with exit code 0


При попытки ввести строку, не являющейся Ip - адрессом или доменным именем, выводится ошибка Invalid Data.

