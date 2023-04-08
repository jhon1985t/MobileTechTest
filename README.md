Android Clean Architecture MobileTechTest
==================================

A project providing demonstrations on how to architect an Android app using Uncle Bob's Clean Architecture approach.

Solid Desing Principles
==================================

A pattern that helps to create code more independent and reusable

Samples
-------

| Sample                             | Description                                                                |
| (mvvm)                             | A clean Model-View-ViewModel (MVVM) architecture sample written in kotlin.   |
| (solid)                            | A clean Design Pattern written in Kotlin.   |
| (hilt)                             | A clean Independency Injection Graph in Kotlin.   |
| (room)                             | A clean DB local.   |

Sample app overview
-------------------

A simple app for read posts, see detail posts, save posts to local DB, save favorite posts, delete a save posts, delete all posts that are not favorite.

Clean Architecture create modules or librarys that are independent from the app main module, following
the definition of layers or entities(each layers or entity have a responsability)

DATA : Content the connections to apis, bds, preferences
USECASES : Content the connections between the data(remote, local) to the view
DOMAIN : Content models
APP : Has vision to all layers and use it

MVVM : MVVM pattern architecture suggested by Google, connect the usecases, models, data with the view,
the view receive a simple object or response to work

General architecture
--------------------

![General architecture](https://www.google.com/imgres?imgurl=https%3A%2F%2Fblog.cleancoder.com%2Funcle-bob%2Fimages%2F2012-08-13-the-clean-architecture%2FCleanArchitecture.jpg&tbnid=nPr36GbgH40TfM&vet=12ahUKEwi9-o7IyJr-AhXKYzABHdXXBmgQMygAegUIARC8AQ..i&imgrefurl=https%3A%2F%2Fblog.cleancoder.com%2Funcle-bob%2F2012%2F08%2F13%2Fthe-clean-architecture.html&docid=VlRChWq2s0_MKM&w=772&h=567&q=clean%20architecture&ved=2ahUKEwi9-o7IyJr-AhXKYzABHdXXBmgQMygAegUIARC8AQ)

JHON JAROL TABARES OROZCO
