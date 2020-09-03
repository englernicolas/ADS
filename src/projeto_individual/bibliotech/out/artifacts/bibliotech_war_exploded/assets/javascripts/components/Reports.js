export default {
    name: 'Reports',
    data: () => ({
    }),
    template: /*html*/ `
        <div>
            <div class="text-center my-5"><span class="grey--text text--darken-3 text-h2 font-weight-bold">Relatórios</span></div>
                                
            <v-divider></v-divider>

            <div class="mx-16 my-5">
                <span class="text-h4">Empréstimos:</span>
                <v-btn class="ml-5 mb-2" color="primary">Gerar</v-btn>
            </div>
            <v-divider></v-divider>
            <div class="mx-16 my-5">
                <span class="text-h4">Livros mais emprestados:</span>
                <v-btn class="ml-5 mb-2" color="primary">Gerar</v-btn>
            </div>
        </div>`
}