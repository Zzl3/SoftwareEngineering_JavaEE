<template>
    <Contentfield>
        <div>
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/admin' }">管理员页面</el-breadcrumb-item>
                <el-breadcrumb-item><a href="/admin/admincategory">书籍管理</a></el-breadcrumb-item>
            </el-breadcrumb>
            <div class="search" style="height:50px">
                <div style="height:5px">
                    <el-input v-model="search" size="mini" placeholder="输入isbn搜索"
                        style="width: 60%;float:left;display:inline-block" />
                    <el-button icon="el-icon-search" type="primary" circle size="mini" @click="searchcategory(search)"
                        style="float:left;display:inline-block">
                    </el-button>
                </div>
                <div style="white-space:nowrap; margin-left: 230px ; margin-top: -10px;"><button id="button1"
                        @click="dialogFormVisible = true">添加书籍</button>
                        <el-dialog :visible.sync="dialogFormVisible">
                        <Myform></Myform>
                    </el-dialog>
                </div>
            </div>
            <el-scrollbar>
                <el-table stripe style="width: 100%" height=600 :data="tableData">
                    <el-table-column label="ISBN" prop="isbn" width="150">
                    </el-table-column>
                    <el-table-column label="BookName" prop="bookname" width="100">
                    </el-table-column>
                    <el-table-column label="Author" prop="author" width="100">
                    </el-table-column>
                    <el-table-column label="Type" prop="type" width="80">
                    </el-table-column>
                    <el-table-column label="Price" prop="price" width="60">
                    </el-table-column>
                    <el-table-column label="Publisher" prop="publisher" width="100">
                    </el-table-column>
                    <el-table-column label="BookAbstract" prop="bookabstract" width="250">
                    </el-table-column>
                    <el-table-column label="Operation" align="right" width="200">
                        <template slot-scope="scope">
                            <button class="custom-btn btn-13" @click="dialogFormVisible = true">修改书籍信息</button>
                            <!-- <button color="#626aef" @click="dialogFormVisible = true">修改书籍信息</button> -->
                            <el-dialog :visible.sync="dialogFormVisible">
                                <Myform></Myform>
                            </el-dialog>
                            <button class="custom-btn btn-5" @click="takedown(scope.row); refresh()"><span>下架此书籍</span></button>
                            <!-- <el-button size="mini" type="danger" @click="takedown(scope.row); refresh()">下架此书籍</el-button> -->
                        </template>
                    </el-table-column>
                </el-table>
            </el-scrollbar>
        </div>
    </Contentfield>
</template>

<script>
import Contentfield from '../../components/AdminCom/Contentfield.vue';
import Myform from "../../components/AdminCom/CategoryForm.vue"


export default {
    name: "categorylist",
    components: {
        Contentfield,
        Myform,
    },
    data() {
        return {
            tableData: [],
            search: "",
            dialogFormVisible: false,
        };
    },
    mounted() {
        this.getdata();
    },
    methods: {
        getdata() {
            let vm = this;
            this.$axios({
                url: "/admin/getallcategory/",
                method: "get",  //这块用post？
            }).then((res) => {
                vm.tableData = [];
                for (let item of res.data) {
                    // console.log(item);
                    vm.tableData.push(item);
                }
            });
        },
        refresh() {
            this.$router.go(0)
        },
        searchcategory(isbn) {
            let vm = this;
            console.log(isbn);
            if (isbn == "" || isbn == null) {
                this.getdata();
            }
            else {
                this.$axios({
                    url: "/searchcategory/",
                    method: "get",
                    params: {
                        content: isbn,
                        ifblur: false,
                        standard: "isbn"
                    }
                }).then((res) => {
                    vm.tableData = [];
                    if (res != null) {
                        console.log(res.data);
                        vm.tableData.push(res.data[0]);
                    }
                })
            }
        },
        takedown(row) {
            console.log(row.isbn);
            this.$axios({
                url: "/admin/deletecategory/",
                method: "post",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {
                    isbn: row.isbn,
                },
            }).then((res) => {
                console.log(res);
            })
        }
    }
}
</script>

<style scoped>
.el-breadcrumb {
    height: 50px;
}

.el-table__body-wrapper::-webkit-scrollbar {
    width: 8px;
    /*滚动条宽度*/
    height: 8px;
    /*滚动条高度*/
}

.el-table__body-wrapper::-webkit-scrollbar-track {
    border-radius: 10px;
    /*滚动条的背景区域的圆角*/
    -webkit-box-shadow: inset 0 0 6px rgba(238, 238, 238, 0.3);
    background-color: #eeeeee;
    /*滚动条的背景颜色*/
}

.el-table__body-wrapper::-webkit-scrollbar-thumb {
    border-radius: 10px;
    /*滚动条的圆角*/
    -webkit-box-shadow: inset 0 0 6px rgba(145, 143, 0143, 0.3);
    background-color: rgb(145, 143, 143);
    /*滚动条的背景颜色*/
}

.search {
    margin-bottom: 20px;
    width: 300px;

}

#button1 {
    padding: 1.3em 3em;
    font-size: 12px;
    text-transform: uppercase;
    letter-spacing: 2.5px;
    font-weight: 500;
    color: #000;
    background-color: #fff;
    border: none;
    border-radius: 45px;
    box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
    transition: all 0.3s ease 0s;
    cursor: pointer;
    outline: none;
    height: 40px;
    display: inline-block;
}

#button1:hover {
    background-color: #23c483;
    box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
    color: #fff;
    transform: translateY(-7px);
}

#button1:active {
    transform: translateY(-1px);
}

/* 5 */
.btn-5 {
  width: 100px;
  height: 40px;
  line-height: 42px;
  padding: 0;
  border: none;
  background: rgb(255,27,0);
  background: linear-gradient(0deg, rgba(255,27,0,1) 0%, rgba(251,75,2,1) 100%);
}
.btn-5:hover {
  color: #f0094a;
  background: rgb(238, 145, 145);
  box-shadow:none;
}
/* .btn-5:before,
.btn-5:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #f0094a;
  box-shadow:
   -1px -1px 5px 0px #fff,
   7px 7px 20px 0px #0003,
   4px 4px 5px 0px #0002;
  transition:400ms ease all;
} */
/* .btn-5:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
/* 
.btn-5:hover:before,
.btn-5:hover:after{
  width:100%;
  transition:800ms ease all;
} */

/* 13 */
.btn-13 {
  background-color: #086af2;
background-image: linear-gradient(315deg, #65bded 0%, #65a0ed 74%);
  border: none;
  z-index: 1;
  height:40px;
  width: 100px;
  margin-bottom: 30px;
}
.btn-13:after {
  position: absolute;
  content: "";
  width: 100%;
  height: 0;
  bottom: 0;
  left: 0;
  z-index: -1;
  border-radius: 5px;
   background-color: #4dccc6;
background-image: linear-gradient(315deg, #4dccc6 0%, #96e4df 74%);
  box-shadow:
   -7px -7px 20px 0px #fff9,
   -4px -4px 5px 0px #fff9,
   7px 7px 20px 0px #0002,
   4px 4px 5px 0px #0001;
  transition: all 0.3s ease;
}
.btn-13:hover {
  color: #fff;
}
.btn-13:hover:after {
  top: 0;
  height: 100%;
}
.btn-13:active {
  top: 2px;
}

</style>