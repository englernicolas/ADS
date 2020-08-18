export default {
    name: 'PayLoanModal',
    template: /*html*/ `
    <div>
        <v-form class="mx-6" ref="form" v-model="valid">
            <v-container>
                <v-row>
                    <v-col class="text-center">
                        <v-btn color="primary" class="white--text text-lg-right">
                            Confirmar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
