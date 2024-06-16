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
      </tr>
      </thead>
      <tbody>
      <tr v-for="post in posts" :key="post.id">
        <td>{{ post.id }}</td>
        <td>{{ post.title }}</td>
        <td>{{ post.author }}</td>
      </tr>
      </tbody>
    </table>

    <div class="pagination">
      <button v-for="page in pages" :key="page">{{ page }}</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      posts: [
        { id: 1, title: '첫 번째 게시글', author: '작성자1' },
        { id: 2, title: '두 번째 게시글', author: '작성자2' },
        { id: 3, title: '세 번째 게시글', author: '작성자3' },
        // 더 많은 게시글 데이터 추가
      ],
      pages: [1, 2, 3, 4, 5], // 페이지 번호 배열
    };
  },
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