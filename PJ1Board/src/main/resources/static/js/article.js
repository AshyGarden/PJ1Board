const deleteButton = document.getElementById("delete-btn"); //削除ボタン

//if (deleteButton) {
//    deleteButton.addEventListener("click", (event) => {
//        let id = document.getElementById("article-id").value;
//        //fetch apiを利用してHTTP DELETE要請を送る
//        fetch(`/api/articles/${id}`, {
//            method: "DELETE",
//        }).then(() => {
//            alert("削除完了!");
//            location.replace("/articles");
//        }); //サーバーからの応答後に受信したコールバック関数
//    });
//}
if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        function success() {
            alert("削除完了!");
            location.replace('/articles');
        }

        function fail() {
            alert('削除に失敗しました.');
            location.replace('/articles');
        }

        httpRequest('DELETE',`/api/articles/${id}`, null, success, fail);
    });
}

const modifyButton = document.getElementById("modify-btn");

//if (modifyButton) {
//    modifyButton.addEventListener("click", (event) => {
//        let params = new URLSearchParams(location.search);
//        let id = params.get("id");
//
//        //fetch apiを利用してHTTP PUT要請を送る
//        fetch(`/api/articles/${id}`, {
//            method: "PUT",
//            headers: {
//                "Content-Type": "application/json",
//            },
//            body: JSON.stringify({
//                title: document.getElementById("title").value,
//                content: document.getElementById("content").value,
//            }),
//        }).then(() => {
//            alert("修正完了!");
//            location.replace(`/articles/${id}`);
//        }); //サーバーからの応答後に受信したコールバック関数
//    });
//}

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        body = JSON.stringify({
            title: document.getElementById('title').value,
            content: document.getElementById('content').value
        })

        function success() {
            alert("修正完了!");
            location.replace(`/articles/${id}`);
        }

        function fail() {
            alert('修正に失敗しました.');
            location.replace(`/articles/${id}`);
        }

        httpRequest('PUT',`/api/articles/${id}`, body, success, fail);
    });
}

const createButton = document.getElementById("create-btn");

//if (createButton) {
//    //fetch apiを利用してHTTP POST要請を送る
//    createButton.addEventListener("click", (event) => {
//        fetch("/api/articles", {
//            method: "POST",
//            headers: {
//                "Content-Type": "application/json",
//            },
//            body: JSON.stringify({
//                title: document.getElementById("title").value,
//                content: document.getElementById("content").value,
//            }),
//        }).then(() => {
//            alert("登録完了!");
//            location.replace("/articles");
//        }); //サーバーからの応答後に受信したコールバック関数
//    });
//}

if (createButton) {
    // 登録ボタンをクリックすると/api/articlesにリクエストを送る
    createButton.addEventListener('click', event => {
        body = JSON.stringify({
            title: document.getElementById('title').value,
            content: document.getElementById('content').value
        });
        function success() {
            alert("登録完了!");
            location.replace('/articles');
        };
        function fail() {
            alert('登録に失敗しました.');
            location.replace('/articles');
        };

        httpRequest('POST','/api/articles', body, success, fail)
    });
}

// ログアウト機能
const logoutButton = document.getElementById('logout-btn');

if (logoutButton) {
    logoutButton.addEventListener('click', event => {
        function success() {
            // ローカル ストレージに保存されているアクセス トークンを削除
            localStorage.removeItem('access_token');

            // クッキーに保存されているリフレッシュ トークンを削除
            deleteCookie('refresh_token');
            location.replace('/login');
        }
        function fail() {
            alert('ログアウトに 失敗しました.');
        }

        httpRequest('DELETE','/api/refresh-token', null, success, fail);
    });
}

// 쿠키를 가져오는 함수
function getCookie(key) {
    var result = null;
    var cookie = document.cookie.split(';');
    cookie.some(function (item) {
        item = item.replace(' ', '');

        var dic = item.split('=');

        if (key === dic[0]) {
            result = dic[1];
            return true;
        }
    });

    return result;
}

// 쿠키를 삭제하는 함수
function deleteCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

// HTTP 요청을 보내는 함수
function httpRequest(method, url, body, success, fail) {
    fetch(url, {
        method: method,
        headers: { // 로컬 스토리지에서 액세스 토큰 값을 가져와 헤더에 추가
            Authorization: 'Bearer ' + localStorage.getItem('access_token'),
            'Content-Type': 'application/json',
        },
        body: body,
    }).then(response => {
        if (response.status === 200 || response.status === 201) {
            return success();
        }
        const refresh_token = getCookie('refresh_token');
        if (response.status === 401 && refresh_token) {
            fetch('/api/token', {
                method: 'POST',
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('access_token'),
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    refreshToken: getCookie('refresh_token'),
                }),
            })
                .then(res => {
                    if (res.ok) {
                        return res.json();
                    }
                })
                .then(result => { // 재발급이 성공하면 로컬 스토리지값을 새로운 액세스 토큰으로 교체
                    localStorage.setItem('access_token', result.accessToken);
                    httpRequest(method, url, body, success, fail);
                })
                .catch(error => fail());
        } else {
            return fail();
        }
    });
}

