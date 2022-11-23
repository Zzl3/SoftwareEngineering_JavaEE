<template>
    <Contentfield>
        <div>
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/admin' }">管理员页面</el-breadcrumb-item>
                <el-breadcrumb-item><a href="/admin/adminuser">用户管理</a></el-breadcrumb-item>
            </el-breadcrumb>
            <el-table style="width: 100%" :data="tableData">
                <el-table-column label="Userid" prop="id">
                </el-table-column>
                <el-table-column label="UserName" prop="username">
                </el-table-column>
                <el-table-column align="right">
                    <template slot="header" slot-scope="scope">
                        <el-input v-model="search" size="mini" placeholder="输入用户名搜索" style="width: 60% " />
                        <el-button icon="el-icon-search" type="primary" circle size="mini"></el-button>
                    </template>
                    <template slot-scope="scope">
                        <el-button v-if="scope.row.status==='banned'" type="warning" size="mini" @click="changestatus(scope.$index, scope.row);refresh()">解封此用户</el-button>
                        <el-button v-else size="mini" type="danger" @click="changestatus(scope.$index, scope.row);refresh()">封禁此用户</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </Contentfield>
</template>

<script>
import Contentfield from '../../components/AdminCom/Contentfield.vue';

export default {
    name: "userlist",
    components: {
        Contentfield,
    },
    data() {
        return {
            tableData: [],
        };
    },
    mounted(){
        this.getdata();
    },
    methods: {
        getdata() {
            let vm = this;
            this.$axios({
                url: "/admin/getalluser",
                method: "get",  //这块用post？
            }).then((res) => {
                for (let item of res.data) {
                    // console.log(item);
                    vm.tableData.push(item);
                }
            });
        },
        refresh(){
            this.$router.go(0)
        },
        changestatus(index, row) {
            console.log(index, row);
            this.$axios({
                url:"/admin/manageuser",
                method:"post",
                headers:{
                    'Content-Type':'application/json'
                },
                data:{
                    username:row.username
                }
            }).then((res)=>{
                console.log(res);
            })
        },
        searchuser(){

        }
    }
}
</script>

<style scoped>
.el-breadcrumb {
    height: 50px;
}

</style>
