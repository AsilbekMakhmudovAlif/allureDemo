base_git_url = "https://github.com/AsilbekMakhmudovAlif/allureDemo.git"
task_branch = "${TEST_BRANCH_NAME}"
currentBuild.displayName = "$task_branch"


node {
       withEnv(["branch=${task_branch}", "base_url=${base_git_url}"]) {
        try {
            stage("Run tests") {
                try {
                        getProject("$base_git_url", "$task_branch")
                        labelledShell(label: "Run simple tests", script: "./mvnw test")
                    } finally {
                        echo "Some failed tests"
                    }
            }
        } finally {
            stage("Generate allure and move to public nginx") {
                generateAllure()
                labelledShell(label: "Move allure results to nginx public directory", script: '''
                timestamp=$(date +%F_%T)
                folder=${branch}_allure_${timestamp}
                mv allure-report ${folder}
                cp -R ${folder} /var/www/html/
                echo "http://localhost:5555/${folder}"
                ''')
            }
        }
    }
}


def getProject(String repo, String branch) {
    cleanWs()
    checkout scm: [
            $class           : 'GitSCM', branches: [[name: branch]],
            userRemoteConfigs: [[
                                        url: repo
                                ]]
    ]
}


def generateAllure() {
    allure([
            includeProperties: true,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: 'build/allure-results']]
    ])
}
