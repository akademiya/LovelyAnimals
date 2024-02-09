Generally you've started out by cloning a repo or you've run git init and created one.

You then edit or create files in that repo.

You then need to stage those file to be committed.

git add <file1> <file2> ...

You can see what's been staged with git status

If everything looks good you can commit those changes

git commit -m "My commit message"

If you've cloned a remote repository, and you have permissions to push to it

git push <remote> <branch> so something like git push origin master

You can view your remotes with git remote -v

You can add a remote if you don't see the remote you need in the list git remote add <give it a name> <the URL to the repo> so something like git remote add upstream https://github.com/me/myrepo.git

And then push to it git push upstream master
