const deleteButton = document.getElementById("delete-btn"); //削除ボタン

if (deleteButton) {
    deleteButton.addEventListener("click", (event) => {
        let id = document.getElementById("article-id").value;
        //fetch apiを利用してHTTP DELETE要請を送る
        fetch(`/api/articles/${id}`, {
            method: "DELETE",
        }).then(() => {
            alert("削除完了!");
            location.replace("/articles");
        }); //サーバーからの応答後に受信したコールバック関数
    });
}

const modifyButton = document.getElementById("modify-btn");

if (modifyButton) {
    modifyButton.addEventListener("click", (event) => {
        let params = new URLSearchParams(location.search);
        let id = params.get("id");

        //fetch apiを利用してHTTP PUT要請を送る
        fetch(`/api/articles/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
            }),
        }).then(() => {
            alert("修正完了!");
            location.replace(`/articles/${id}`);
        }); //サーバーからの応答後に受信したコールバック関数
    });
}

const createButton = document.getElementById("create-btn");

if (createButton) {
    //fetch apiを利用してHTTP POST要請を送る
    createButton.addEventListener("click", (event) => {
        fetch("/api/articles", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
            }),
        }).then(() => {
            alert("登録完了!");
            location.replace("/articles");
        }); //サーバーからの応答後に受信したコールバック関数
    });
}
