<template>
  <div class="body">
    <aside><img src="@/assets/img/404/Mirror.png" alt="404 Image" /></aside>
    <main>
      <h1>Sorry!</h1>
      <p>
        要么你不够酷，无法访问此网页，要么它不存在<em>. . . 你的社交生活.</em>
      </p>
      <a href=""
        ><router-link to="/Home"><button>返回主页！</button></router-link></a
      >
    </main>
  </div>
</template>

<script>
export default {
  name: "errPage",
};
</script>

<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css2?family=Fontdiner+Swanky&family=Roboto:wght@500&display=swap");
* {
  box-sizing: 0;
  margin: 0;
  padding: 0;
  cursor: url("../../assets/img/404/cursors-edge.png"), auto;
}

.body {
  min-width: 100%;
  min-height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background: -webkit-gradient(
    linear,
    left top,
    right top,
    color-stop(50%, white),
    color-stop(50%, #383838)
  );
  background: linear-gradient(to right, white 50%, #383838 50%);
  font-family: "Roboto", sans-serif;
  font-size: 18px;
  font-weight: 500;
  line-height: 1.5;
  color: white;
  display: -webkit-box;
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  margin: 0 auto;
}
div > * {
  display: -webkit-box;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  flex-flow: column;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  height: 100vh;
  max-width: 500px;
  width: 100%;
  padding: 2.5rem;
}

aside {
  background-image: url("../../assets/img/404/right-edges.png");
  background-position: top right;
  background-repeat: no-repeat;
  background-size: 25px 100%;
}
aside img {
  display: block;
  height: auto;
  width: 100%;
}

main {
  text-align: center;
}
main h1 {
  font-family: "Fontdiner Swanky", cursive;
  font-size: 4rem;
  color: #c5dc50;
  margin-bottom: 1rem;
}
main p {
  margin-bottom: 2.5rem;
}
main p em {
  font-style: italic;
  color: #c5dc50;
}
main button {
  font-family: "Fontdiner Swanky", cursive;
  font-size: 1rem;
  color: #383838;
  border: none;
  background-color: #f36a6f;
  padding: 1rem 2.5rem;
  -webkit-transform: skew(-5deg);
  transform: skew(-5deg);
  -webkit-transition: all 0.1s ease;
  transition: all 0.1s ease;
  cursor: url("../../assets/img/404/cursors-eye.png"), auto;
}
main button:hover {
  background-color: #c5dc50;
  -webkit-transform: scale(1.15);
  transform: scale(1.15);
}

@media (max-width: 700px) {
  body {
    background: #383838;
    font-size: 16px;
  }

  div {
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    flex-flow: column;
  }
  div > * {
    max-width: 700px;
    height: 100%;
  }

  aside {
    background-image: none;
    background-color: white;
  }
  aside img {
    max-width: 300px;
  }
}
</style>
