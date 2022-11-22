/*这里是存储全局变量的地方*/

export default {
    nowuserid: "1",
    imageurl:"https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
    setnowuserid(nowuserid) {
        this.nowuserid = nowuserid;
    },
    setimageurl(imageurl) {
        this.imageurl= imageurl;
    },
}