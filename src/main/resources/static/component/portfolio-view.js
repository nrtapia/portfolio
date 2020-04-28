Vue.component('portfolio-view', {
  template: `
      <div>
        <div class="row">
          <div class="col-sm-12">
            <div class="d-flex align-items-center">
              <form-search v-on:find-by-id-event="getPortfolio( $event )"></form-search>
              <div v-if="loading" class="spinner-border ml-auto" role="status"
                   aria-hidden="true"></div>
            </div>
          </div>

          <div class="col-sm-12" v-if="error">
            <div v-if="error" class="alert alert-danger" role="alert">
              {{ error }}
            </div>
          </div>

          <div class="row">
            <div class="col-sm-4">
              <portfolio-photo v-bind:image="portfolio.photo"></portfolio-photo>
              <timeline v-bind:portfolio="portfolio"></timeline>
            </div>
            <div class="col-sm-8">
              <portfolio-card v-bind:portfolio="portfolio"
                              v-bind:key="portfolio.id"></portfolio-card>
            </div>
          </div>
          
          <portfolio-update v-bind:portfolio="portfolio"></portfolio-update> 
        </div>
      </div>
    `,
  data: () => ({
    portfolio: {photo: "/images/user-empty.png"},
    loading: false,
    error: null
  }),
  watch: {
    '$route': 'getPortfolio'
  },
  methods: {
    async getPortfolio(id) {
      this.error = null;
      this.loading = true;
      console.log('Get portfolio id:', id);

      let response = await fetch(`/portfolio/${id}`);
      if (response.status === 200) {
        this.portfolio = await response.json();
      } else {
        this.error = response.status === 404 ? 'Portfolio not found!'
            : 'Error to process the search.';
      }

      this.loading = false;
    }
  }
});