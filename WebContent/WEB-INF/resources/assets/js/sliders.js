let c = [
  "blogging",
  "cooking",
  "dancing",
  "entertaining",
  "movies",
  "music",
  "foody",
  "singing",
  "sports",
  "technology",
  "business",
  "games",
];
c.forEach((a) => {
  let slider = document.getElementById(a);
  let output = document.getElementById(`value-${a}`);
  output.innerHTML = slider.value;
  slider.oninput = function () {
    output.innerHTML = String(this.value) + "/10";
  };
});
