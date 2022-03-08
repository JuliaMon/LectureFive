package ru.netology

class Donut {
    val isDonut: Boolean = false // запись доступна только платным подписчикам VK Donut;
    val paidDuration: Int =
        0 // время, в течение которого запись будет доступна только платным подписчикам VK Donut;
    val placeholder: Placeholder =
        Placeholder()  // заглушка для пользователей, которые не оформили подписку VK Donut. Отображается вместо содержимого записи.


    val canPublishFreeCopy: Boolean =
        false // можно ли открыть запись для всех пользователей, а не только подписчиков VK Donut;
    val editMode: EditMode =
        EditMode() // информация о том, какие значения VK Donut можно изменить в записи. Возможные значения:
}
