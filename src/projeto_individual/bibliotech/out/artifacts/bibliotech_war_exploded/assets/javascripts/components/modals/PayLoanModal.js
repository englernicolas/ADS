export default {
    name: 'PayLoanModal',
    template: /*html*/ `
    <div>
        <v-form class="mx-6" ref="form" v-model="valid">
            <v-container>
                <v-row>
                    <span><v-icon class="mb-1">mdi-alert</v-icon> - Deseja mesmo realizar o pagamento? Fazendo isso você assume que o valor foi pago pelo aluno.</span>
                </v-row>
                <v-row>
                    <v-col class="text-center">
                        <v-btn color="primary" class="white--text text-lg-right">
                            Confirmar
                            <v-icon small class="ml-1">mdi-alert</v-icon>
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
