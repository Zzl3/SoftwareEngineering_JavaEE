<template>
    <div>
        <form class="basic-grey" id="myform">
            <h1>
                修改书籍信息
                <span>请按照下方规则修改书籍信息(若不填写则不会修改)</span>
            </h1>
            <label>
                <span>isbn:</span>
                <input id="isbn" name="isbn" type="text" placeholder="请输入书籍isbn" />
            </label>
            <label>
                <span>bookname:</span>
                <input id="bookname" type="text" name="bookname" placeholder="请输入书名" />
            </label>

            <label>
                <span>author:</span>
                <input id="author" type="text" name="author" placeholder="请输入作者" />
            </label>
            <label>
                <span>publisher:</span>
                <input id="publisher" type="text" name="publisher" placeholder="请输入出版社" />
            </label>

            <label>
                <span>type:</span>
                <input id="type" type="text" name="type" placeholder="请输入书籍类型" />
            </label>

            <label>
                <span>price:</span>
                <input id="price" type="text" name="price" placeholder="请输入价格" />
            </label>

            <label>
                <span>abstract:</span>
                <textarea id="bookabstract" name="bookabstract" placeholder="请输入书籍摘要"></textarea>
            </label>
            <label>
                <!-- <span>&nbsp;</span>
          <input
            type="button"
            class="button"
            style="background: white; color: #e27575; margin-right: 20px"
            value="清空"
            @click="cleardata"
          /> -->
                <span>&nbsp;</span>
                <input type="button" class="button" value="确定" @click="submitdata" />
            </label>
        </form>
    </div>
</template>
  
<script>
export default {
    data() {
        return {
            url: "",
        };
    },
    methods: {
        submitdata() {
            var _this = this;
            var formData = new FormData(myform); // form为表单对象
            _this.$axios.post("admin/managecategory/", {
                isbn: formData.get("isbn"),
                author: formData.get("author"),
                bookname: formData.get("bookname"),
                price: formData.get("price"),
                bookabstract: formData.get("bookabstract"),
                age: formData.get("age"),
                type: formData.get("type")
            });
            this.$message({
                message: "已成功修改信息",
                type: "success",
            });
            this.$router.go(0);
        },
    },
};
</script>
  
<style scoped>
.basic-grey {
    margin-left: auto;
    margin-right: auto;
    max-width: 500px;
    background: #f7f7f7;
    padding: 25px 15px 25px 10px;
    font: 12px Georgia, "Times New Roman", Times, serif;
    color: #888;
    text-shadow: 1px 1px 1px #fff;
    border: 1px solid #e4e4e4;
}

.basic-grey h1 {
    font-size: 25px;
    padding: 0px 0px 10px 40px;
    display: block;
    border-bottom: 1px solid #e4e4e4;
    margin: -10px -15px 30px -10px;
    color: #888;
}

.basic-grey h1>span {
    display: block;
    font-size: 11px;
}

.basic-grey label {
    display: block;
    margin: 0px;
}

.basic-grey label>span {
    float: left;
    width: 20%;
    text-align: right;
    padding-right: 10px;
    margin-top: 10px;
    color: #888;
}

.basic-grey input[type="text"],
.basic-grey input[type="password"],
.basic-grey input[type="email"],
.basic-grey textarea,
.basic-grey select {
    border: 1px solid #dadada;
    color: #888;
    height: 30px;
    margin-bottom: 16px;
    margin-right: 6px;
    margin-top: 2px;
    outline: 0 none;
    padding: 3px 3px 3px 5px;
    width: 70%;
    font-size: 12px;
    line-height: 15px;
    box-shadow: inset 0px 1px 4px #ececec;
    -moz-box-shadow: inset 0px 1px 4px #ececec;
    -webkit-box-shadow: inset 0px 1px 4px #ececec;
}

.basic-grey textarea {
    padding: 5px 3px 3px 5px;
}

.basic-grey select {
    background: #fff;
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    text-indent: 0.01px;
    text-overflow: "";
    width: 70%;
    height: 35px;
    line-height: 25px;
}

.basic-grey textarea {
    height: 100px;
}

.basic-grey .button {
    background: #e27575;
    border: none;
    padding: 10px 25px 10px 25px;
    color: #fff;
    box-shadow: 1px 1px 5px #b6b6b6;
    border-radius: 3px;
    text-shadow: 1px 1px 1px #9e3f3f;
    cursor: pointer;
}

.basic-grey .button:hover {
    background: #cf7a7a;
}
</style>