import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'StudentLoansModal',
    props: {
        loans: Array
    },
    data: () => ({
    }),
    template: /*html*/ `
    <div>
        <v-card v-for="loan in loans" class="mx-6 my-5">
            <v-container>
                <v-row class="mx-3">
                    <v-col>
                        <v-row>
                            <span class="font-weight-bold">Título:</span>
                        </v-row>
                        <v-row>
                            <span>{{loan.title}}</span>
                        </v-row>
                    </v-col>
                    <v-col>
                        <v-row>
                            <span class="font-weight-bold">Autor:</span>
                        </v-row>
                        <v-row>
                            <span>{{loan.author}}</span>
                        </v-row>
                    </v-col>
                    <v-col>
                        <v-row>
                            <span class="font-weight-bold">Gênero:</span>
                        </v-row>
                        <v-row>
                            <span>{{loan.gender}}</span>
                        </v-row>
                    </v-col>
                    <v-col>
                        <v-row>
                            <span class="font-weight-bold">Data Emp:</span>
                        </v-row>
                        <v-row>
                            <span>{{loan.loanDate}}</span>
                        </v-row>
                    </v-col>
                    <v-col>
                        <v-row>
                            <span class="font-weight-bold">Data Ent:</span>
                        </v-row>
                        <v-row>
                            <span>{{loan.deliveryDate}}</span>
                        </v-row>
                    </v-col>
                    <v-col v-if="loan.debt">
                        <v-row>
                            <span class="font-weight-bold red--text">Multa:</span>
                        </v-row>
                        <v-row>
                            <span class="red--text">R$ {{loan.debt}}</span>
                        </v-row>
                    </v-col>
                </v-row>
            </v-container>
        </v-card>
    </div>`
}
