<template>
  <div class="container">
    <nav class="navbar">
      <div class="logo">게시판</div>
      <ul class="nav-links">
        <li><a href="#">홈</a></li>
        <li><a href="#">게시글</a></li>
        <li><a href="#">회원가입</a></li>
        <li><a href="#">로그인</a></li>
      </ul>
    </nav>

    <div class="banner">
      <img src="banner.jpg" alt="배너 이미지" />
    </div>

    <table class="post-table">
      <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성시간</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(post, index) in posts" :key="post.id">
        <td>{{ totalElements - index -((currentPage-1)*pageSize)}}</td>
        <td>{{ post.subject }}</td>
        <td>{{ post.nickname }}</td>
        <td>{{ post.postTime }}</td>
      </tr>
      </tbody>
    </table>

    <div class="pagination">
      <button v-for="index in totalPages" :key="index"
              @click = 'getPostPage(index)'>
        {{ index }}
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      posts: [
      ],
      pageSize: 0,
      totalPages: 0,
      totalElements: 0,
      currentPage: 0,
    };
  },
  computed:{

  },
  created() {
    this.getPostList();
  },
  methods: {
    getPostList(){
      this.currentPage = window.location.href.split("=")[1];
      axios.get("/post/list?page=" + (this.currentPage-1))
          .then((res) => {
            this.posts = res.data.content;
            this.pageSize = res.data.size;
            this.totalPages = res.data.totalPages;
            this.totalElements = res.data.totalElements;
            console.log("게시글이 로드되었습니다.", res);
          })
          .catch((res) => {
            console.log("게시글을 불러오는 데 실패했습니다.", res);
          })
    },
    getPostPage(index){

      return window.location.href = "/post/list?page=" + index;
    }
  }
};
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.navbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #333;
  color: #fff;
  padding: 10px;
  z-index: 999;
}

.logo {
  font-size: 24px;
  font-weight: bold;
}

.nav-links {
  list-style: none;
  display: flex;
}

.nav-links li {
  margin-left: 20px;
}

.nav-links a {
  color: #fff;
  text-decoration: none;
}

.banner {
  margin-top: 60px;
}

.banner img {
  width: 100%;
  height: auto;
}

.post-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

.post-table th,
.post-table td {
  padding: 10px;
  text-align: left;
  border-bottom: 1px solid #ccc;
}

.pagination {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  display: flex;
  justify-content: center;
  padding: 10px;
  background-color: #fff;
}

.pagination button {
  margin: 0 5px;
  padding: 5px 10px;
  background-color: #333;
  color: #fff;
  border: none;
  cursor: pointer;
}

.nav-links li {
  margin-left: 20px;
}

.nav-links a {
  color: #fff;
  text-decoration: none;
  padding: 5px 10px;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.nav-links a:hover {
  background-color: #555;
}

.pagination button {
  margin: 0 5px;
  padding: 8px 16px;
  background-color: #333;
  color: #fff;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.pagination button:hover {
  background-color: #555;
}

.pagination button:active {
  background-color: #222;
}
</style>