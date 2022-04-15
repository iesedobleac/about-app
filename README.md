# About App Screen

Este repositorio se trata de una plantilla para una pantalla tipo "Sobre la app", realizado con la biblioteca Preference de Jetpack's. 

Puedes añadirla en tus proyectos con tan realizar los cambios que voy a explicar más abajo.

![](https://i.imgur.com/pvyzxjJ.png)

![](https://i.imgur.com/RKC0qWU.png)

### ¿Cómo la añado a mi proyecto?

Su instalación es muy sencilla. En primer lugar descargamos el proyecto a nuestro ordenador.

`Desde aquí en GitHub: botón verde que pone Code -> Download ZIP.`

Una vez lo extraemos vas a ver que tenemos varias carpetas, de entre todas ellas la que nos interesa es la que se llama aboutlibrary.

Vamos a la raiz de nuestro proyecto y pegamos aquí esta carpeta. Manos a la obra en Android Studio.

En primer lugar vamos al fichero `settings.gradle` de nuestro proyecto y vamos a configurar el directorio que acabamos de añadir para que sea detectado como un módulo. Añadimos:

```
include ':aboutlibrary'
```
> En el caso de cambiar el nombre a la carpeta y en vez de llamarla `aboutlibrary` le pongamos otro nombre, hay que modificar este valor para adecuarlo al nombre que le hemos puesto.

</br>

A continuación, vamos al fichero `build.gradle` a nivel de aplicación y añadimos:

```
dependencies {
    ···
    implementation project(path: ':aboutlibrary')
    ···
}
```

Hacemos una sincronización de nuestros archivos gradle y veremos como el icono del directorio que habiamos añadido ha cambiado, esto es señal de que lo que hemos realizado hasta ahora es correcto.

### ¿Como modifico los literales?

Hay un fichero de literales diferente al que se genera de manera automática por el proyecto, para poder tener mejor localizado donde debemos realizar los cambios. Este fichero lo encontrarás en `res -> values -> mutable_strings.xml`

```xml
<resources>

    <!-- Enlace hacía el apartado de todas tus aplicaciones de Google Play -->

    <string name="other_apps_link" translatable="false">https://play.google.com/store/apps/collection/cluster?clp=igM4ChkKEzY3NDY1MTU1MzI0MDYzMjQzODQQCBgDEhkKEzY3NDY1MTU1MzI0MDYzMjQzODQQCBgDGAA%3D:S:ANO1ljJ_J6I&amp;gsr=CjuKAzgKGQoTNjc0NjUxNTUzMjQwNjMyNDM4NBAIGAMSGQoTNjc0NjUxNTUzMjQwNjMyNDM4NBAIGAMYAA%3D%3D:S:ANO1ljKZSDE</string>

    <!-- Enlace hacía tu política de privacidad -->

    <string name="privacy_policy_link" translatable="false">https://isaacdelosreyes.netlify.app/eula</string>

    <!-- Enlace hacía tu perfil de usuario de Twitter -->

    <string name="follow_twitter_link" translatable="false">https://twitter.com/iesedobleac</string>

    <!-- Nombre de tu usuario de Twitter -->

    <string name="follow_twitter_summary" translatable="false">\@iesedobleac</string>

    <!-- Enlace de tu página de desarrollador de Google Play -->

    <string name="developer_page_link" translatable="false">https://play.google.com/store/apps/dev?id=6746515532406324384</string>

    <!-- Email de contacto, personal o el que quieras usar -->

    <string name="email_contact_summary" translatable="false">isaacdelosredi@gmail.com</string>

    <!-- Texto que aparece en el footer de las preferencias -->

    <string name="developer_copyright" translatable="false">Developer by Isaac de los Reyes Díaz</string>

</resources>
```

### ¿Como cambio el icono de la aplicación?

La plantilla está dividida en tres partes principales, un header, las secciones y un footer. El logo se encuentra en un componente de Material Design llamado `ShapeableImageView`, ubicado en el header que es una vista customizada de Preference. Solo hay que sustituir la imagen que está cargando actualmente por la de tu aplicación.

```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    ···

    <LinearLayout
        android:id="@+id/about_header__container__information"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center_vertical"
        android:gravity="center"
        android:orientation="vertical"
        android:paddingHorizontal="@dimen/dp20">

        <com.google.android.material.imageview.ShapeableImageView
            android:id="@+id/about_header__img__app_logo"
            android:layout_width="@dimen/dp68"
            android:layout_height="@dimen/dp68"
            android:src="@drawable/logo_test"
            app:shapeAppearanceOverlay="@style/CornerSize50Percent" />

        ···

    </LinearLayout>

</FrameLayout>
```

### Modo noche

![light-mode](https://i.imgur.com/KCiRlyy.png "light-mode") ![night-mode](https://i.imgur.com/FBXGCrB.png "night-mode")

La librería cuenta con modo noche u oscuro como se aprecia como se aprecia en las imágenes. Podemos modificar y adaptar los colores desde la carpeta de recursos en cada fichero de temas, colores y atributos.

### Secciones

No voy a crear un tutorial especifico sobre como añadir una nueva sección, pero es realmente sencillo si seguimos la guía oficial de Google:

```
https://developer.android.com/guide/topics/ui/settings
```

En el caso de esta librería vamos a encontrar el xml de configuración en la carpeta de recursos `res -> xml -> fragment_about_app.xml`

### Idiomas

De base, se encuentra traducida en inglés y en español, pero podemos añadir tantos idiomas como queramos añadiendo la carpeta de recursos correspondiente y traduciendo todos los literales que hay en ella o necesitemos.

### Consideraciones

Está pensada para ser usada como un `fragment`, ya que extiende de `PreferenceFragmentCompat` la cual es una subclase de `Fragment`, asi que debes de tener un `activity` donde poder cargarlo.

La forma en la que lo inicies depende de ti, puedes usar el método habitual para cargar fragments o si lo prefieres, usar `Navigation` de la librería de Jetpack's.
