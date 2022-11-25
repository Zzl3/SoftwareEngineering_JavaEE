<template>
    <Contentfield>
        <div>
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/admin' }">管理员页面</el-breadcrumb-item>
                <el-breadcrumb-item><a href="/admin/admincategory">书籍管理</a></el-breadcrumb-item>
            </el-breadcrumb>
            <div class="search">
                <el-input v-model="search" size="mini" placeholder="输入isbn搜索" style="width: 60% ; float:left" />
                <el-button icon="el-icon-search" type="primary" circle size="mini" @click="searchcategory(search)">
                </el-button>
            </div>
            <el-scrollbar>
                <el-table style="width: 100%" height=600 :data="tableData">
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
                            <button color="#626aef"  @click="dialogFormVisible = true">修改书籍信息
                            </button>
                            <el-dialog :visible.sync="dialogFormVisible">
                                <Myform></Myform>
                            </el-dialog>
                            <el-button size="mini" type="danger" @click="takedown(scope.row); refresh()">下架此书籍
                            </el-button>
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
</style>
