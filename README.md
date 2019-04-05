# ZipA

[![](https://jitpack.io/v/komatatsu/ZipA.svg)](https://jitpack.io/#komatatsu/ZipA)

Androidで郵便番号から住所を得るためのライブラリです。  
日本の郵便番号にしか対応していません。  
[日本郵政が公開している郵便番号データ](https://www.post.japanpost.jp/zipcode/download.html)を利用しています。

# Installation

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
dependencies {
    implementation 'com.github.komatatsu:ZipA:1.0'
}
```

# 使い方

```kotlin
val address1 = ZipA.search(context, "1000001")
```

- 数値七桁とハイフン区切りに対応
- 存在しない場合はnullを返す
