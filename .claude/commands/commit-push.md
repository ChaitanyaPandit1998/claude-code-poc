---
allowed-tools: Bash(git status:*), Bash(git add:*), Bash(git diff:*), Bash(git log:*), Bash(git commit:*), Bash(git push:*)
argument-hint: "[commit-message]"
description: Stage changes, create commit, and push to remote repository
---

# Git Commit and Push

## Context

You need to commit and push changes to the repository. First, gather information about the current state:

- Current git status: !`git status`
- Staged changes: !`git diff --cached`
- Unstaged changes: !`git diff`
- Recent commits for style reference: !`git log -3 --oneline`

## Your task

Follow the git workflow from the project guidelines:

1. **Review changes**: Analyze both staged and unstaged changes
2. **Stage files**: Add relevant files to staging (skip .class files and IDE files)
3. **Create commit message**:
   - If the user provided a custom message as an argument, use it
   - Otherwise, generate a descriptive commit message based on the changes
   - Follow the repository's commit style (see recent commits)
   - Use present tense, imperative mood
   - Explain what changed and why
   - Keep summary line concise
4. **Commit**: Create the commit with the message, always include:
   ```
   Co-Authored-By: Claude Sonnet 4.5 <noreply@anthropic.com>
   ```
5. **Push**: Push the commit to the remote repository
6. **Confirm**: Show final git status

## Important notes

- Do NOT commit compiled files (.class files)
- Do NOT commit IDE-specific files
- Follow project's coding standards from CLAUDE.md
- Use HEREDOC format for commit messages to ensure proper formatting
- Verify git status after pushing
