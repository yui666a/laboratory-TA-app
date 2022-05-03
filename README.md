# laboratory-TA-app

B3 情報システム工学実験 で用いるアプリケーション

## 基本方針

* 目標：8 月末完成（あまり負担になってもいけないので、完成した時に運用開始しましょう）  
* メンテナンスを容易にするため、  
①Web アプリだけで完結する。  
② 変更（座席配置変更等）はクライアント側（HTML,CSS,JavaScript）の修正だけですむようにする。  
③ なるべくクライアント側も学生と TA で同じソースコードにする。  
* なるべく視覚的に（文字ではなく図形や色で）情報を参照できるようにする。  
* とりあえずは専門実験室だけで良いが、最終的には基礎実験室についても操作できるようにする。  

## 必要機能

1. 挙手  

----------------
**以下は、もし可能な場合に**  

2. 課題チェック（できれば欲しいが、課題チェックは評価に反映されるので、なりすましや誤操作等の防止が必要）
3. チャット (非必須条件のため，一旦置いておきます)
4. 出席確認（出席は評価に反映されるので、誤操作やなりすましなりすまし等の防止が必要）
----------------
### 使用端末

* 学生：実験室 PC  
* TA：自身のスマホ(Wifi）、（プロバイダも？)、PC(実験室 PC or その他の PC)  

### 各学生のデータ

(1)学生の状態  
* 質問なし  
* 挙手  
* TA 対応中  

(2)時間  
* （手を挙げてからの）待ち時間
* （もしできたら）TA 対応開始からの経過時間  
※もし時間がかかっている場合は別の TA が補助できるように。

## 利用方法

### 学生

1. ブラウザで指定の URL にアクセスすると，出席管理ページが表示される。  
   (1'. 学籍番号を入力してログインする？）
2. このページには、「座席」と「待ち行列」（とボタン）が表示される。 
3. 「座席」の表示部分で、自分の座席の場所、自分の状態、手を挙げている場合の待ち時間がわかる。 
4. 「待ち行列」の表示部分で、手を挙げて待っている人の人数、自分が何番目か、（待っている人全員の各待ち時間？）がわかる。
5. 学生は（ボタンをクリックするなどして）自分の状態を変更できる。
6. 学生は（ボタンをクリックするなどして）その時点での自分の状態をサーバに送信できる。
7. 最終的には、自分の情報と待ち行列の表示はリアルタイムで更新されるようにする。

### TA

1. ブラウザで指定の URL にアクセスすると，出席管理ページが表示される。  
   (1'. 名前が表示される場合には個人情報漏洩を防止するため、ログインする？）
2. このページには、「座席」と「待ち行列」（とボタン）が表示される。 
3. 「座席」の表示部分で、全学生の状態、手を挙げている場合の場合の待ち時間がわかる。 
4. 「待ち行列」の表示部分で、手を挙げて待っている人全員の座席と待ち時間、（と名前）がわかる。
5. 誤操作防止のため、TA は学生の状態を変更できない？。
6. 最終的には、表示される状態がリアルタイムで更新されるようにする。

※3.4 はできれば名前も分かると良い

## 送受信データ

(1)ブラウザ-->Servlet
* その学生の状態（と学籍番号 or 名前？）

(2)Servlet-->ブラウザ
* 各学生の状態、待ち時間、座席の座標、（と学籍番号？）  
※学生は自分が参照して良い情報のみがサーバから送られる

(3)DB レコード
* RID、学生の状態、ボタンを押した時間、IP アドレス、（と学籍番号？）

(4)その他
* クライアント側の IP アドレスは Servlet 上で取得可能らしい。
* 座席の配置情報は別ファイルに保存しておいて JavaScript で読み込む。
* （必要な場合のみ）学籍番号と名前の対応表は別ファイルに保存しておいて JavaScript で読み込む。
* サーバ側では、最終的には DB に保存するが、最初は Session などに入れておき DB なしでもテストできるようにする。

## 言語
* サーバ側：Java
* クライアント側：HTML,CSS,JavaScript（と Vue.js）

## （とりあえずの）データ送受信方法
* http の同期通信
* クライアントからは POST で送信
* サーバからの戻り値は HTML の\<div\>に埋め込む

それで動作したら、Vue の非常期通信を使って、
その都度サーバに POST で送信し、サーバからの戻り値は文字列化されて返ってくるようにする。

## 質問事項

1. 上記はソケット通信の利用を想定しています．そのサーバを使用する予定でしょうか？　また，socket 通信は可能でしょうか？

> A. とりあえず...来年以降の他の TA でもメンテナンスできるようにするため、使わないでやりたいと思います。
> できればクライアント（学生実験室 PC）には何もおかないで済むようにしたいです。
> できればサーバ側にも Tomcat 以外は何も入れない何も動かさないで済むようにしたいです。
