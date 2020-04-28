Vue.component('portfolio-card', {
  props: ['portfolio'],
  template: `
      <div>
        <h1> {{ portfolio.title }}</h1>
        <div class="card">
          <div class="card-body">
            <h4 class="card-title text-muted">My Work Experience</h4>
            <p class="card-text">
              {{ portfolio.description }}
            </p>
          </div>
        </div>
      </div>
    `
});
