# Memory Gallery
----------------

## Описание
Memory Gallery — JavaFX-приложение для создания и воспроизведения интерактивных слайд-шоу с текстовыми пометками, тегами и графическими оверлеями. Пользователь загружает изображения, добавляет эмоции и заметки, настраивает анимацию и музыку, после чего сохраняет коллекцию в JSON и делится ею. Проект демонстрирует работу с JavaFX UI, медиаплеером, файловой системой и сериализацией.

---

## Технологический стек
- Java 21  
- JavaFX 21 (controls, FXML, media, swing)  
- Maven (сборка и запуск)  
- Jackson Databind для JSON  
- Desktop standalone приложение

---

## Статус
Beta

---

## Ссылка на репозиторий и демо
[GitHub репозиторий](https://github.com/godmozzarella/memory-gallery)

---

## Основные возможности
- Добавление фото и видео-слайдов с выбором типа (Photo/Motion)  
- Редактор тегов, заметок и текста оверлея с предпросмотром  
- Графические эмоции поверх изображения: выбор формы, цвета, размера, прозрачности  
- Настройка анимации (Fade/Slide/Scale) и длительности для каждого слайда  
- Подключение фоновой музыки и управление воспроизведением  
- Захват текущего слайда в PNG и сохранение/загрузка коллекций в JSON  
- Автовоспроизведение с режимами Sequential, Loop и Bounce

---

## Архитектура
- **Пакет `controller`**  
  - `MainController` управляет UI, связывает форму с сервисами, реагирует на действия пользователя и поддерживает предпросмотр.  
- **Пакет `model`**  
  - Доменные сущности (`Slide`, `SlideOverlay`, `SlideNote`, `AnimationSettings`, `EmotionOverlay`) описывают содержимое слайда.  
  - DTO (`SlideDto`, `SlideCollectionDto`, `SlideOverlayDto`, `EmotionOverlayDto`, `SlideNoteDto`, `AnimationSettingsDto`) используются для обмена через JSON.  
  - `SlideDescriptor` собирает данные из формы, `SlideIterator` инкапсулирует навигацию, `PlaybackStatus` и `PlaybackStatusBuilder` формируют статусную строку.  
- **Пакет `service`**  
  - `SlideShowService` управляет ObservableList слайдов и использует `SlideFactoryProvider` + фабрики (`PhotoSlideFactory`, `MotionSlideFactory`) для создания объектов.  
  - `SlideCollectionService` отвечает за экспорт/импорт коллекций, `SlidePlaybackService` за анимацию, `AudioService` за работу с медиаплеером.  
- **Пакет `util`**  
  - Вспомогательные классы (`AppPaths`, `FxDurationUtils`, `NodeSnapshotUtil`) для путей, конвертации длительностей и экспортов изображений.  
- **FXML и ресурсные файлы** описывают интерфейс и биндинги для контроллера, а `MemoryGalleryApplication` является точкой входа.

---

## Диаграмма классов
```mermaid
classDiagram
    class MemoryGalleryApplication
    class MainController{
        +initialize()
        +handleAddSlide()
        +handleSaveCollection()
        +handlePlayShow()
    }
    class SlideShowService{
        +createSlide()
        +addSlides()
        +moveSlide()
        +buildStatus()
    }
    class SlideCollectionService{
        +save()
        +load()
    }
    class SlidePlaybackService{
        +play()
        +stop()
    }
    class AudioService{
        +load()
        +play()
        +pause()
        +stop()
    }
    class SlideIterator{
        +current()
        +next()
        +previous()
        +moveTo()
    }
    class SlideFactoryProvider{
        +getFactory()
    }
    class PhotoSlideFactory
    class MotionSlideFactory
    SlideFactoryProvider --> PhotoSlideFactory : создает
    SlideFactoryProvider --> MotionSlideFactory : создает
    PhotoSlideFactory ..|> SlideFactory
    MotionSlideFactory ..|> SlideFactory
    class SlideFactory{
        <<interface>>
        +create()
    }
    class Slide{
        +title
        +type
        +getOverlay()
    }
    class SlideOverlay{
        +tags
        +overlayText
    }
    class SlideNote{
        +text
        +updatedAt
    }
    class AnimationSettings{
        +effect
        +duration
        +amplitude
    }
    class SlideDto
    class SlideCollectionDto
    class SlideOverlayDto
    class EmotionOverlayDto
    class SlideNoteDto
    class AnimationSettingsDto
    MainController --> SlideShowService
    MainController --> SlideCollectionService
    MainController --> SlidePlaybackService
    MainController --> AudioService
    SlideShowService --> SlideIterator
    SlideShowService --> SlideFactoryProvider
    SlideShowService --> Slide
    Slide --> SlideOverlay
    Slide --> SlideNote
    Slide --> AnimationSettings
    SlideCollectionService --> SlideCollectionDto
    SlideCollectionDto --> SlideDto
    SlideDto --> SlideOverlayDto
    SlideDto --> SlideNoteDto
    SlideDto --> AnimationSettingsDto
    SlideOverlayDto --> EmotionOverlayDto
```

---

## Установка и запуск
1. Клонировать репозиторий:  
```bash
git clone https://github.com/godmozzarella/memory-gallery.git
```
2. Перейти в директорию проекта:  
```bash
cd memory-gallery
```
3. Собрать проект:  
```bash
mvn clean install
```
4. Запустить JavaFX-приложение:  
```bash
mvn javafx:run
```

---

## Использование
1. Нажмите «Добавить» и выберите изображение/видео-файл — файл копируется в локальное хранилище.  
2. Настройте заголовок, тип слайда, теги и текст оверлея; добавьте эмоцию и заметку.  
3. Выберите эффект анимации, длительность и амплитуду.  
4. Управляйте позициями слайдов (вверх/вниз/в начало/в конец).  
5. Сохраняйте отдельный слайд в PNG или экспортируйте всю коллекцию в JSON.  
6. Загружайте коллекции обратно и выбирайте режим Playback (Sequential/Loop/Bounce).  
7. Подключайте музыку, управляйте воспроизведением и запускайте автоматический показ.

---

## Особенности реализации
- **JSON-персистентность**: `SlideCollectionService` конвертирует доменные модели в DTO и обратно через Jackson с нормализацией путей.  
- **Анимации**: `SlidePlaybackService` включает Fade/Slide/Scale-транзишены и сбрасывает состояние узла после завершения.  
- **Навигация**: `SlideIterator` упорядочивает переходы по списку и поддерживает клаузы first/last/bounce.  
- **Музыка**: `AudioService` оборачивает JavaFX `MediaPlayer` и синхронизируется с автопоказом.  
- **Снимки**: `NodeSnapshotUtil` делает PNG-шоты слайдов через JavaFX Snapshot API.  
- **Пути и каталоги**: `AppPaths` централизовано задаёт директории для изображений, коллекций и экспортов.

---

## Зависимости
- JavaFX 21 (controls, fxml, media, swing)  
- Jackson Databind 2.18  
- JUnit 5 (для тестов)  
- Maven Compiler Plugin + JavaFX Maven Plugin

---

## Ограничения и известные проблемы
- Тесты пока отсутствуют и проверка проводится вручную.  
- Поддерживаются только локальные файлы; облачные источники не подключены.  
- Форматы аудио ограничены расширениями, разрешёнными JavaFX Media (mp3/wav/aac).  
- Для корректного автопоказа требуется JavaFX Media с аппаратным ускорением (на headless-средах может не работать).

---

## Контакты и поддержка
Пишите вопросы и предложения в разделе Issues GitHub репозитория.

---

## Скриншоты
Скриншоты интерфейса будут добавлены после подготовки макетов (раздел `docs/screenshots/`). Пока можно использовать инструмент `Сохранить слайд` для генерации собственного примера.

